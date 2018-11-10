private static final String DOWN = "D";
private static final String UP = "U";

// Complete the countingValleys function below.
static int countingValleys(int n, String s) throws IOException {
    int valleysCount = 0;
    int currentDepth = 0;

    for (int x = 0; x < n; x++) {
        String nextStep = String.valueOf(s.charAt(x));

        if (currentDepth == -1 && nextStep.equals(UP)) {
            valleysCount++;
        }

        if (nextStep.equals(DOWN)) {
            currentDepth--;
        } else {
            currentDepth++;
        }
    }

    return valleysCount;
}
