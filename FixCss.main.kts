import java.io.File

val baseDirs = listOf(
    File("C:/Users/srira/AndroidStudioProjects/BeaconLink/app/src/main/assets/frontend"),
    File("C:/Users/srira/AndroidStudioProjects/BeaconLink/extracted_frontend/stitch_offgrid_emergency_network_app")
)

for (baseDir in baseDirs) {
    if (!baseDir.exists()) continue
    
    baseDir.walkTopDown().forEach { file ->
        if (file.isFile && file.name == "code.html") {
            var content = file.readText()
            var changed = false
            
            // Fix body: replace min-h-screen with h-screen, ensure flex flex-col h-screen
            val bodyRegex = Regex("""(<body[^>]*class=")([^"]*)(")""")
            content = bodyRegex.replace(content) { matchResult ->
                val prefix = matchResult.groupValues[1]
                val classes = matchResult.groupValues[2]
                val suffix = matchResult.groupValues[3]
                
                val classList = classes.split("\\s+".toRegex()).toMutableList()
                classList.remove("min-h-screen")
                classList.remove("h-screen")
                classList.add("h-screen")
                
                if (!classList.contains("flex")) classList.add("flex")
                if (!classList.contains("flex-col")) classList.add("flex-col")
                
                changed = true
                prefix + classList.joinToString(" ") + suffix
            }

            // Fix main: flex-1 overflow-y-auto
            val mainRegex = Regex("""(<main[^>]*class=")([^"]*)(")""")
            content = mainRegex.replace(content) { matchResult ->
                val prefix = matchResult.groupValues[1]
                val classes = matchResult.groupValues[2]
                val suffix = matchResult.groupValues[3]
                
                val classList = classes.split("\\s+".toRegex()).toMutableList()
                if (!classList.contains("flex-1")) classList.add("flex-1")
                if (!classList.contains("overflow-y-auto")) classList.add("overflow-y-auto")
                // remove overflow-hidden if present
                classList.remove("overflow-hidden")
                
                changed = true
                prefix + classList.joinToString(" ") + suffix
            }
            
            // Fix header: flex-shrink-0 or shrink-0
            val headerRegex = Regex("""(<header[^>]*class=")([^"]*)(")""")
            content = headerRegex.replace(content) { matchResult ->
                val prefix = matchResult.groupValues[1]
                val classes = matchResult.groupValues[2]
                val suffix = matchResult.groupValues[3]
                
                val classList = classes.split("\\s+".toRegex()).toMutableList()
                if (!classList.contains("shrink-0") && !classList.contains("flex-shrink-0")) {
                    classList.add("shrink-0")
                }
                
                changed = true
                prefix + classList.joinToString(" ") + suffix
            }

            // Fix nav: fixed bottom-0 z-50
            val navRegex = Regex("""(<nav[^>]*class=")([^"]*)(")""")
            content = navRegex.replace(content) { matchResult ->
                val prefix = matchResult.groupValues[1]
                val classes = matchResult.groupValues[2]
                val suffix = matchResult.groupValues[3]
                
                val classList = classes.split("\\s+".toRegex()).toMutableList()
                classList.remove("absolute")
                if (!classList.contains("fixed")) classList.add("fixed")
                if (!classList.contains("bottom-0")) classList.add("bottom-0")
                if (!classList.contains("z-50") && !classList.contains("z-40")) classList.add("z-50")
                
                changed = true
                prefix + classList.joinToString(" ") + suffix
            }

            if (changed) {
                file.writeText(content)
            }
        }
    }
}
println("Done modifying HTML files.")
