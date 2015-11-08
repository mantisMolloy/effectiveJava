package chapter2.item2;

/**
 * Created by tmolloy on 08/11/2015.
 */
public class Facts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    //private condtructor
    private Facts(FactsBuilder builder) {
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class FactsBuilder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories      = 0;
        private int fat           = 0;
        private int carbohydrate  = 0;
        private int sodium        = 0;

        public FactsBuilder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings    = servings;
        }

        public FactsBuilder calories(int val) {
            calories = val;
            return this;
        }
        public FactsBuilder fat(int val) {
            fat = val;
            return this;
        }
        public FactsBuilder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        public FactsBuilder sodium(int val) {
            sodium = val;
            return this;
        }

        public Facts build() {
            return new Facts(this);
        }
    }

    public static void main(String... args){
        FactsBuilder fb = new FactsBuilder(10, 5);
        fb.calories(10);
        Facts f = fb.build();

        Facts f2 = new FactsBuilder(30, 10).calories(10).fat(9).build();
    }
}

