package weapons;

public record Weapon(WeaponType type, String name, int weight) { // Добавлено поле weight

    @Override
    public String toString() {
        return String.format("%s (тип: %s, вес: %d пудов)", name, type, weight); // Теперь учитывается вес
    }
}

