package swimlee.programmers.kakaoblind;

public class LockAndKey {

    int keyLen;
    int lockLen;
    int len;
    int[][] paddingLock;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        LockAndKey lockAndKey = new LockAndKey();
        boolean solution = lockAndKey.solution(key, lock);
        System.out.println("solution = " + solution);
    }

    public boolean solution(int[][] key, int[][] lock) {
        keyLen = key.length;
        lockLen = lock.length;

        len = lockLen + 2 * (keyLen - 1);

        for (int r = 0; r < 4; r++) {
            for (int x = 0; x < len - (keyLen - 1); x++) {
                for (int y = 0; y < len - (keyLen - 1); y++) {
                    paddingLock = initialLock(lock);

                    for (int i = 0; i < keyLen; i++) {
                        for (int j = 0; j < keyLen; j++) {
                            paddingLock[x + i][y + j] += key[i][j];
                        }
                    }

                    if (check()) return true;
                }
            }
            key = rotateKey(key);
        }

        return false;
    }

    private int[][] initialLock(int[][] lock) {
        int[][] paddingLock = new int[len][len];

        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                paddingLock[i + keyLen - 1][j + keyLen - 1] = lock[i][j];
            }
        }

        return paddingLock;
    }

    private boolean check() {
        for (int i = keyLen - 1; i < keyLen - 1 + lockLen; i++) {
            for (int j = keyLen - 1; j < keyLen - 1 + lockLen; j++) {
                if (paddingLock[i][j] != 1) return false;
            }
        }

        return true;
    }

    private int[][] rotateKey(int[][] key) {
        int[][] rotatedKey = new int[keyLen][keyLen];

        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                rotatedKey[j][keyLen - i - 1] = key[i][j];
            }
        }

        return rotatedKey;
    }
}
