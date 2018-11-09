// Complete the sockMerchant function below.
static int sockMerchant(int n, int[] ar) {
    int totalPairs = 0;
    for (int x = 0; x < n; x++) {
        if (ar[x] <= 0) {
            continue;
        }
        for (int y = x + 1; y < n; y++) {
            if (ar[x] == ar[y]) {
                ar[y] = -1;
                totalPairs++;
                break;
            }
        }
    }
    return totalPairs;
}
