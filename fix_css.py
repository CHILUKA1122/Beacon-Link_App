import os
import re

base_dirs = [
    r'C:\Users\srira\AndroidStudioProjects\BeaconLink\app\src\main\assets\frontend',
    r'C:\Users\srira\AndroidStudioProjects\BeaconLink\extracted_frontend\stitch_offgrid_emergency_network_app'
]

for base_dir in base_dirs:
    for root, _, files in os.walk(base_dir):
        for f in files:
            if f == 'code.html':
                filepath = os.path.join(root, f)
                with open(filepath, 'r', encoding='utf-8') as file:
                    content = file.read()

                # Make sure body has flex flex-col h-screen
                if '<body' in content:
                    content = re.sub(r'(<body[^>]*class=")([^"]*)(")',
                                    lambda m: m.group(1) + ' '.join(set((m.group(2).replace('min-h-screen', 'h-screen') + ' flex flex-col h-screen').split())) + m.group(3),
                                    content)

                # Make sure top header has shrink-0 (or flex-shrink-0)
                if '<header' in content:
                    content = re.sub(r'(<header[^>]*class=")([^"]*)(")',
                                    lambda m: m.group(1) + ' '.join(set((m.group(2) + ' shrink-0').split())) + m.group(3),
                                    content)

                # Make sure main has flex-1 overflow-y-auto
                if '<main' in content:
                    content = re.sub(r'(<main[^>]*class=")([^"]*)(")',
                                    lambda m: m.group(1) + ' '.join(set((m.group(2) + ' flex-1 overflow-y-auto').split())) + m.group(3),
                                    content)

                # Make sure bottom nav has fixed bottom-0 or shrink-0
                # Wait, the prompt says "fixed bottom-0 or flex-shrink-0 if in the flow".
                # For safety, if it has 'absolute', we change to 'fixed' to make sure it sticks relative to viewport
                # Actually, some navs might already be absolute. Let's just ensure they are fixed bottom-0 if they have bottom.
                if '<nav' in content:
                    content = re.sub(r'(<nav[^>]*class=")([^"]*)(")',
                                    lambda m: m.group(1) + ' '.join(set((m.group(2).replace('absolute', 'fixed') + ' fixed bottom-0 z-50').split())) + m.group(3),
                                    content)

                # Wait, I should also ensure `<main>` has `overflow-y-auto` instead of `overflow-hidden` or something.
                with open(filepath, 'w', encoding='utf-8') as file:
                    file.write(content)

print("Done")