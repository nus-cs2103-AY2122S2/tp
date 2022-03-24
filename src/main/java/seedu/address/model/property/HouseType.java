package seedu.address.model.property;

/**
 * All types of houses are listed here
 */
public enum HouseType {
    ANY {
        @Override
        public String toString() {
            return "Any";
        }
    },
    APARTMENT {
        @Override
        public String toString() {
            return "Apartment";
        }
    },
    BUNGALOW {
        @Override
        public String toString() {
            return "Bungalow";
        }
    },
    CONDOMINIUM {
        @Override
        public String toString() {
            return "Condomunium";
        }
    },
    COLONIA {
        @Override
        public String toString() {
            return "Colonia";
        }
    },
    HDB_FLAT {
        @Override
        public String toString() {
            return "HDB Flat";
        }
    },
    SEMIDETACHED{
        @Override
        public String toString() {
            return "Semi-Detached";
        }
    },
    MANSION{
        @Override
        public String toString() {
            return "Mansion";
        }
    },
    NULLHOUSETYPE {
        @Override
        public String toString() {
            return "Not a house type";
        }
    }
}

