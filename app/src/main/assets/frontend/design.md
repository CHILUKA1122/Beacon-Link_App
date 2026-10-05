OFFGRID DESIGN SYSTEM (DESIGN.md)
Project: OFFGRID – AI Powered Offline Emergency Communication Network Platform: Android (Material 3 Expressive) Version: V2.0 Theme: Futuristic Emergency Communication + AI + Bluetooth Mesh Design Goal: Premium Google I/O 2026 quality mobile application.
DESIGN PRINCIPLES
OFFGRID should feel trustworthy, futuristic, intelligent, fast and life-saving.
The experience should combine Google's Material 3 Expressive with Apple's fluid animations and Nothing OS minimalism.
Every interaction must feel alive.
Core Design Values


Modern.

Clean.

Emergency-first.

AI powered.

Glassmorphism.

Motion-rich.

Accessible.

Production-ready.
COLOR SYSTEM
Dark Theme
Primary Background: #050816
Secondary Background: #0D1524
Surface: #111827
Glass Surface: rgba(255,255,255,0.08)
Glass Border: rgba(255,255,255,0.15)
Primary Gradient: #2563FF → #00D4FF
Secondary Gradient: #7C3AED → #5B21B6
Emergency Gradient: #FF3B5C → #FF8A00
Success: #1DB954
Warning: #F59E0B
Danger: #DC2626
AI Accent: #8B5CF6
Location Accent: #14B8A6
Mesh Accent: #22D3EE
Bluetooth Accent: #3B82F6
Text Primary: #FFFFFF
Text Secondary: #CBD5E1
Text Muted: #64748B
Divider: rgba(255,255,255,0.08)
Glow Colors: Blue Glow. Purple Glow. Red Glow. Green Glow.
Light Theme
Background: #F7FAFF
Surface: #FFFFFF
Card: rgba(255,255,255,0.75)
Glass Blur: 24
Primary: #2563FF
Secondary: #7C3AED
Text: #0F172A
Muted: #64748B
GRADIENTS
Hero Gradient: Blue → Cyan
SOS Gradient: Red → Orange
AI Gradient: Purple → Indigo
Success Gradient: Green → Emerald
Analytics Gradient: Blue → Violet
Mesh Gradient: Cyan → Purple
TYPOGRAPHY
Primary Font: Google Sans
Secondary Font: Inter
Monospace: JetBrains Mono
Display Large 40sp Bold
Display Medium 32sp
Headline 28sp
Title Large 24sp
Title Medium 20sp
Body Large 16sp
Body Medium 15sp
Caption 13sp
Button 15sp SemiBold
SPACING SYSTEM
Grid System: 8dp
Padding Horizontal: 24dp
Padding Vertical: 20dp
Card Radius: 28dp
Button Radius: 20dp
Input Radius: 18dp
FAB Radius: 100%
Bottom Sheet Radius: 32dp
Icon Radius: 16dp
ICONOGRAPHY
Use Material Symbols Rounded.
Icons have gradient fills.
Active icons glow.
Inactive icons use muted color.
Emergency icons pulse.
Bluetooth icons emit animated waves.
SHADOW SYSTEM
Soft Shadow
Blur 24
Opacity 18%
Floating Shadow
Blur 48
Glow Shadow
Blue Purple Red
Cards have floating elevation.
GLASSMORPHISM
All hero cards use:
Background blur. 12% white opacity. Gradient border. Soft glow. Noise texture.
Never use flat cards.
COMPONENT LIBRARY
Primary Button
Height: 56dp
Gradient Fill.
Rounded pill.
Glow on press.
Ripple animation.
Loading state.
Success state.
Disabled state.
Secondary Button
Glass background.
Gradient border.
Icon + text.
Hover glow.
Emergency Button
Large circular button.
120dp diameter.
Breathing pulse.
Gradient red/orange.
Ripple expands outward continuously.
Countdown animation supported.
Floating Action Button
Blue/Cyan gradient.
Soft shadow.
Morph animation when tapped.
Center FAB reserved for SOS.
Cards
Hero Card
Large gradient.
Glass blur.
Animated background particles.
Statistics.
CTA.
Info Card
Rounded.
Icon.
Title.
Description.
Arrow.
Tap animation.
Device Card
Avatar.
Distance.
Signal.
Battery.
Connect button.
Expandable.
Analytics Card
Gradient header.
Chart preview.
Interactive.
Input Fields
Rounded.
Glass background.
Floating labels.
Animated focus border.
Leading icon.
Trailing action.
Voice icon support.
Search Bar
Rounded capsule.
Blur background.
Animated search icon.
Filter chip support.
Chips
Rounded.
Gradient active state.
Animated selection.
Filter chips.
Status chips.
Language chips.
Emergency chips.
Bottom Navigation
Fixed.
Glass dock.
Five icons.
Animated indicator.
Shared element transition.
Icons scale when selected.
Top App Bar
Large title.
Sticky.
Blur on scroll.
Profile avatar.
Status chip.
Notification bell.
Bottom Sheets
Blurred.
Rounded top.
Scrollable.
Snap positions.
Drag indicator.
MOTION DESIGN
Animation Duration: 200ms 300ms 500ms
Spring Physics.
Ease Out.
Shared Element Transition.
Fade Through.
Container Transform.
Ripple.
Hero Transition.
Morph Animation.
Scale Animation.
Slide Up.
Slide Down.
Blur Fade.
Shimmer.
Skeleton Loading.
Particle Success Animation.
Bluetooth Radar Pulse.
SOS Breathing Pulse.
Waveform Live Animation.
Typing Dots Animation.
Connection Beam Animation.
Map Pin Bounce.
Network Packet Flow Animation.
SCREEN SPECIFICATIONS
Splash
Animated particle logo.
Bluetooth shield forms.
Gradient background.
Loading wave.
Auto transition.
Onboarding
3 swipeable pages.
Animated illustrations.
Progress dots.
Skip.
Next.
Get Started.
Permissions
Animated permission cards.
Bluetooth.
Nearby Devices.
Location.
Microphone.
Notifications.
Permission success animation.
Dashboard
Sticky header.
Greeting.
Mesh Status Hero Card.
Nearby Devices Preview.
Quick Actions Grid.
Recent Messages.
Emergency Feed.
AI Suggestions.
Battery + Signal Widget.
Everything scrollable.
Pull to Refresh.
Nearby Devices
Animated radar.
Search.
Filter chips.
Scrollable cards.
Expandable devices.
Connect.
Disconnect.
Invite.
Signal animation.
Connection Flow
Pairing animation.
Success animation.
Failure retry.
Progress ring.
Chat
Scrollable history.
Typing animation.
Read receipts.
Emoji picker.
Attachment menu.
Voice messages.
Location cards.
Swipe reply.
Long press actions.
Search messages.
Jump to latest.
Pinned messages.
Encryption badge.
Walkie Talkie
Hold to Talk.
Waveform.
Recording timer.
Cancel gesture.
Playback.
Nearby participants.
Bluetooth strength.
Live Call
Caller avatar.
Live waveform.
Mute.
Speaker.
Bluetooth.
Add participant.
End call.
Connection animation.
Offline Map
Dark map.
Zoom.
Compass.
SOS pins.
Nearby teammates.
Animated routes.
Bottom sheet.
Filters.
Share Location
Live share toggle.
One-time share.
Emergency share.
Map preview.
Animated pulse.
SOS
Breathing emergency button.
Countdown.
Emergency categories.
Priority selector.
Broadcast animation.
Cancel.
Success screen.
Incoming SOS
Full screen alert.
Distance.
Map preview.
Accept rescue.
Ignore.
Navigation CTA.
AI Assistant
Chat interface.
Voice input.
Quick prompts.
Emergency detector.
Translation card.
Animated avatar.
Translation
Language selector.
Swap animation.
Conversation history.
Play audio.
Copy translation.
Offline badge.
Mesh Visualization
Interactive network graph.
Nodes pulse.
Packets travel.
Zoom.
Tap node.
Relay visualization.
Analytics
Messages today.
Relay packets.
Coverage radius.
Battery usage.
Emergency statistics.
Interactive charts.
Weekly tabs.
Profile
Statistics.
Achievements.
Connected devices.
Emergency history.
Theme selector.
Settings shortcut.
Settings
Grouped categories.
Theme.
Bluetooth Mesh.
AI.
Voice.
Privacy.
Security.
Notifications.
Animations.
Language.
About.
Everything scrollable.
NAVIGATION GRAPH
Splash
↓
Onboarding
↓
Permissions
↓
Dashboard
Dashboard links to:
Chat
Nearby Devices
Walkie Talkie
Voice Call
Offline Map
Location Sharing
SOS
AI Assistant
Analytics
Profile
Profile links to:
Settings
Emergency History
Achievements
Settings links to all preference pages.
Bottom navigation works globally.
Back navigation always returns logically.
INTERACTION RULES
Every icon clickable.
Every card clickable.
Every chip selectable.
Every list item clickable.
Buttons navigate.
Maps respond.
Graphs expand.
Messages animate.
No static components.
SCROLL BEHAVIOUR
Every screen vertical scroll.
Sticky top app bar.
Bottom navigation fixed.
Chat independently scrolls.
Bottom sheets scroll.
Lists lazy load.
Cards resize automatically.
No clipped content.
LOADING STATES
Bluetooth Searching.
No Devices.
Connecting.
Sending Message.
Receiving Message.
Sending SOS.
Receiving SOS.
Loading AI.
Loading Translation.
Location Fetching.
Skeleton shimmer for every list.
EMPTY STATES
No Nearby Devices.
No Messages.
No SOS Alerts.
No Call History.
No Analytics.
No Translation History.
Illustration + CTA on every empty screen.
ERROR STATES
Bluetooth Off.
Location Disabled.
Microphone Permission Missing.
Connection Lost.
Packet Failed.
SOS Failed.
Retry actions included.
AI VISUAL STYLE
AI responses appear in glass cards.
Purple glow.
Streaming typing animation.
Voice waveform while AI listens.
Suggested prompts appear as animated chips.
ACCESSIBILITY
Minimum touch target 48dp.
High contrast.
Screen reader labels.
Color-blind friendly status indicators.
Large font support.
Reduce motion support.
RESPONSIVE RULES
Support Android phones:
360x800
393x852
412x915
428x926
Safe areas respected.
Dynamic Island style status widget optional.
Foldable friendly layouts.
PROTOTYPE REQUIREMENTS
Every screen is connected.
Every CTA navigates.
Every animation plays.
Every list scrolls.
Every interaction has visual feedback.
Prototype should feel like a finished Android application ready for Google Play demo.
END DESIGN.md