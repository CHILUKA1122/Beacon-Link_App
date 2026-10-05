import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FixCss {
    public static void main(String[] args) throws Exception {
        String[] dirs = {
            "C:/Users/srira/AndroidStudioProjects/BeaconLink/app/src/main/assets/frontend",
            "C:/Users/srira/AndroidStudioProjects/BeaconLink/extracted_frontend/stitch_offgrid_emergency_network_app"
        };
        
        for (String dir : dirs) {
            File base = new File(dir);
            if (base.exists()) {
                processDir(base);
            }
        }
        System.out.println("Done.");
    }
    
    private static void processDir(File dir) throws Exception {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                processDir(f);
            } else if (f.getName().equals("code.html")) {
                String content = new String(Files.readAllBytes(f.toPath()), "UTF-8");
                boolean changed = false;
                
                Pattern pBody = Pattern.compile("(<body[^>]*class=\")([^\"]*)(\")");
                Matcher mBody = pBody.matcher(content);
                if (mBody.find()) {
                    String cls = mBody.group(2).replace("min-h-screen", "").replace("h-screen", "");
                    cls += " h-screen flex flex-col";
                    cls = cls.replaceAll("\\s+", " ").trim();
                    content = mBody.replaceFirst(mBody.group(1) + cls + mBody.group(3));
                    changed = true;
                }
                
                Pattern pMain = Pattern.compile("(<main[^>]*class=\")([^\"]*)(\")");
                Matcher mMain = pMain.matcher(content);
                if (mMain.find()) {
                    String cls = mMain.group(2).replace("overflow-hidden", "");
                    if (!cls.contains("flex-1")) cls += " flex-1";
                    if (!cls.contains("overflow-y-auto")) cls += " overflow-y-auto";
                    cls = cls.replaceAll("\\s+", " ").trim();
                    content = mMain.replaceFirst(mMain.group(1) + cls + mMain.group(3));
                    changed = true;
                }
                
                Pattern pHeader = Pattern.compile("(<header[^>]*class=\")([^\"]*)(\")");
                Matcher mHeader = pHeader.matcher(content);
                if (mHeader.find()) {
                    String cls = mHeader.group(2);
                    if (!cls.contains("shrink-0") && !cls.contains("flex-shrink-0")) cls += " shrink-0";
                    cls = cls.replaceAll("\\s+", " ").trim();
                    content = mHeader.replaceFirst(mHeader.group(1) + cls + mHeader.group(3));
                    changed = true;
                }
                
                Pattern pNav = Pattern.compile("(<nav[^>]*class=\")([^\"]*)(\")");
                Matcher mNav = pNav.matcher(content);
                if (mNav.find()) {
                    String cls = mNav.group(2).replace("absolute", "fixed");
                    if (!cls.contains("fixed")) cls += " fixed";
                    if (!cls.contains("bottom-0")) cls += " bottom-0";
                    cls = cls.replaceAll("\\s+", " ").trim();
                    content = mNav.replaceFirst(mNav.group(1) + cls + mNav.group(3));
                    changed = true;
                }
                
                if (changed) {
                    Files.write(f.toPath(), content.getBytes("UTF-8"));
                }
            }
        }
    }
}