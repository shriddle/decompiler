import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Application {

    public static HashMap<String, Integer> decryptPasswords = new HashMap<>();
    public static HashMap<Integer, String> encryptPasswords = new HashMap<>();
    public static List<String> passwords = new ArrayList<>();

    public static String[] seedArray;

    static
    {
        passwords.add("brilliance");
        passwords.add("navy");
        passwords.add("single");
        passwords.add("thigh");
        passwords.add("distinct");
        passwords.add("barrier");
        passwords.add("angle");
        passwords.add("contract");
        passwords.add("suffering");
        passwords.add("definite");
        passwords.add("unlikely");
        passwords.add("leaf");
        passwords.add("bulletin");
        passwords.add("uniform");
        passwords.add("staircase");
        passwords.add("sink");
        passwords.add("bank");
        passwords.add("eagle");
        passwords.add("empirical");
        passwords.add("communication");
        passwords.add("interference");
        passwords.add("negligence");
        passwords.add("unique");
        passwords.add("litigation");
        passwords.add("progress");
        passwords.add("salon");
    }

    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            args = new String[] {"hi there :)"};
        }
        start(args);
    }

    public static void start(String[] args)
    {
        String seed = generateRandomSeed();
        if(args.length > 1)
        {
            seed = args[1];
        }
        seedArray = seed.split(" ");

        String value = args[0];

        for(int i = 0; i < passwords.size(); i++)
        {
            decryptPasswords.put(passwords.get(i), Integer.parseInt(seedArray[i]));
            encryptPasswords.put(Integer.parseInt(seedArray[i]), passwords.get(i));
        }

        System.out.println("Seed: " + prettyArray(Arrays.asList(seedArray)));

        encrypt(value);
        decrypt(value);
    }

    public static void decrypt(String toDecrypt)
    {
        String[] words = toDecrypt.split(" ");

        List<String> letters = new ArrayList<>();

        for (String word : words)
        {
            if (decryptPasswords.containsKey(word))
            {
                letters.add(getLetterFromAlphabetNumber(decryptPasswords.get(word)));
            }
        }

        System.out.print("Decrypted: " + prettyArray(letters));

    }

    public static void encrypt(String toEncrypt)
    {
        List<String> encrypted = new ArrayList<>();
        String[] toEncryptArray = toEncrypt.split("");
        for(int i = 0; i < toEncryptArray.length; i++)
        {
            char letter = toEncrypt.charAt(i);
            if(Character.isLetter(letter))
            {
                encrypted.add(encryptPasswords.get(getAlphabetNumberFromLetter(letter)));
            }
        }
        System.out.println("Encrypted: " + prettyArray(encrypted));
    }

    public static String generateRandomSeed()
    {
        List<Integer> letters = new ArrayList<>();
        while(letters.size() != 26)
        {
            int generated = ThreadLocalRandom.current().nextInt(1,27);
            if(!letters.contains(generated) && generated != 0)
            {
                letters.add(generated);
            }

        }
        return prettyArray(letters);
    }

    public static int getAlphabetNumberFromLetter(char letter)
    {
        return letter - 'a' + 1;
    }

    private static String getLetterFromAlphabetNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }


    public static String prettyArray(List<?> list)
    {
        return list.toString().replace("[", "").replace("]", "").replace(",", "");
    }

}