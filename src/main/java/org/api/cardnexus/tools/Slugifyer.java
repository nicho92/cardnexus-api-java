package org.api.cardnexus.tools;


import java.text.Normalizer;
import java.util.Map;
import java.util.regex.Pattern;


public class Slugifyer {

        private static final Map<String, String> EXTRA_MAPPINGS = Map.of(
                "★", "star",
                "†", "cross",
                "?", "question",
                "!", "exclamation");

        private static final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        private Slugifyer() {
        }

        public static String nameSlug(String name) {
            return nameSlug(name, null);
        }

        public static String nameSlug(String name, String gameSlug) {

            if (name == null || name.isBlank())
                return "";
            
            if(gameSlug==null)
        	return withFallback(name);
            
            return switch (gameSlug) {
                case "fab" -> withFallback(removeFabVariant(name));
                case "mtg" -> withFallback(normalizeMtg(name));
                default -> withFallback(name);
            };
        }

        private static String removeFabVariant(String text) {
            // Bravo (Red) -> Bravo
            return text.replaceFirst("\\s*\\([^)]+\\)$", "");
        }

        private static String normalizeMtg(String text) {
            return text
                    .replace("★", "star")
                    .replace("//", "-");
        }

        private static String withFallback(String text) {

            var slug = slugify(text, true);

            if (slug.isEmpty()) {
                slug = slugify(text, false);
            }

            return slug;
        }

        private static String slugify(String input, boolean strict) {

            if (input == null)
                return "";

            // CardNexus mappings
            for (var entry : EXTRA_MAPPINGS.entrySet()) {
                input = input.replace(entry.getKey(), entry.getValue());
            }

            // Remove accents
            String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
            normalized = DIACRITICS.matcher(normalized).replaceAll("");

            normalized = normalized.toLowerCase().trim();

            if (strict) {
                // Keep only letters/digits/spaces/hyphens
                normalized = normalized.replaceAll("[^a-z0-9\\s-]", "");
            } else {
                // Keep unicode letters as fallback
                normalized = normalized.replaceAll("[^\\p{L}\\p{N}\\s-]", "");
            }

            normalized = normalized
                    .replaceAll("[\\s_-]+", "-")
                    .replaceAll("^-+", "")
                    .replaceAll("-+$", "");

            return normalized;
        }

    
}
