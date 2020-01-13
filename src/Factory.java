import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class Factory {



        interface AnimalConstructorRef extends Supplier<Animal> {
        }

        enum AnimalConstructor {
            DOG(Dog::new),
            CAT(Cat::new);

            private AnimalConstructorRef constructorRef;

            AnimalConstructor(AnimalConstructorRef constructorRef) {
                this.constructorRef = constructorRef;
            }
        }

        static Map<String, AnimalConstructorRef> animalConstructorRefMap = new HashMap<>();

        static {
            initMap();
        }

        private static void initMap() {
            for (AnimalConstructor animal : AnimalConstructor.values()) {
                animalConstructorRefMap.put(animal.name(), animal.constructorRef);
            }
        }

        public static Optional<Animal> create(String type) {
            return Optional.ofNullable(type)
                    .map(String::toUpperCase)
                    .map(animalConstructorRefMap::get)
                    .map(Supplier::get);
        }


        public static void main(String[] args) {
            Optional<Animal> cat = Factory.create("cat");
            Optional<Animal> dog = Factory.create("dog");
            Optional<Animal> nul = Factory.create("qwe");
            System.out.println(cat.get());
            System.out.println(dog.get());
            System.out.println(nul.isEmpty());
        }






}
