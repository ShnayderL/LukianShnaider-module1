public class App {
    public static void main(String[] args) {
        Runner runner = new Runner();
        if (args.length > 0) {
            runner.runWithArgs(args);
        } else {
            runner.runCli();
        }
    }
}
