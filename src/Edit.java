public class Edit
{
    private static String clipboard;

    public static void Copy(String string)
    {
        clipboard = string;
    }

    public static String Paste()
    {
        return clipboard;
    }
}
