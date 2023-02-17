package common;

public enum DragonType {
    WATER,

    UNDERGROUND,

    AIR,

    FIRE;

    public static DragonType parseDragonType(String line) {
        for (DragonType type : DragonType.values()) {
            if (type.toString().equals(line.toUpperCase()))
                return type;
        }
        throw new UnsupportedOperationException("%s - %s".formatted(line, "can't be converted to DragonType"));
    }


    public static String stringValues() {
        StringBuilder line = new StringBuilder();
        for (DragonType type : DragonType.values()) {
            line.append("%s\s".formatted(type.toString()));
        }
        return line.toString().strip();
    }
}
