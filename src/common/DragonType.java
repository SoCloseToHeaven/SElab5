package common;


public enum DragonType {
    WATER,

    UNDERGROUND,

    AIR,

    FIRE;

    /**
     * parses String to DragonType
     * @param line value to parse
     * @return DragonType's value
     * @throws UnsupportedOperationException if value is invalid
     */
    public static DragonType parseDragonType(String line) {
        for (DragonType type : DragonType.values()) {
            if (type.toString().equals(line.toUpperCase()))
                return type;
        }
        throw new UnsupportedOperationException("%s - %s".formatted(line, "can't be converted to DragonType"));
    }

    /**
     * @return all DragonType.values() in one line
     */

    public static String stringValues() {
        StringBuilder line = new StringBuilder();
        for (DragonType type : DragonType.values()) {
            line.append("%s ".formatted(type.toString()));
        }
        return line.toString().strip();
    }
}
