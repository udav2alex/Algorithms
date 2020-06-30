package ru.gressor.algorithms.lesson2;

public class Array {
    private int[] arr;
    private int size;
    private boolean isSorted;

    private Array() {
        this.isSorted = false;
    }

    public Array(int capacity) {
        this();
        arr = new int[capacity];
        this.size = 0;
    }

    public Array(int... args) {
        this();
        this.size = args.length;
        this.arr = args;
    }

    public int get(int index) {
        checkBounds(index);
        return arr[index];
    }

    public void set(int index, int value) {
        checkBounds(index);
        arr[index] = value;
    }

    public int length() {
        return size;
    }

    private void increaseCapacity() {
        if (arr == null || arr.length == 0) {
            arr = new int[1];
        } else {
            int[] temp = arr;
            arr = new int[size * 2];
            System.arraycopy(temp, 0, arr, 0, size);
        }
    }

    public void append(int value) {
        if (size >= arr.length) {
            increaseCapacity();
        }
        arr[size++] = value;
        isSorted = false;
    }

    public int deleteLast() {
        if (size == 0)
            throw new ArrayIndexOutOfBoundsException(-1);

        return arr[--size];
    }

    public void insert(int index, int value) {
        if (index != size) { // решаем, что разрешается добавить элемент следующий за последним
            checkBounds(index);
        }
        if (size >= arr.length) {
            increaseCapacity();
        }
        if (size - index > 0) {
            System.arraycopy(arr, index, arr, index + 1, size - index);
        }
        arr[index] = value;
        size++;
    }

    public boolean deleteValue(int value) {
        int foundAt;
        int count = 0;

        for (int i = 0; i <= size; i++) {
            foundAt = find(value);
            if (foundAt == -1) {
                return count != 0;
            } else {
                deleteByIndex(foundAt);
                count++;
            }
        }

        throw new RuntimeException("found() works incorrectly...");
    }

    private void checkBounds(int index) {
        if (size <= index || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);
    }

    public int deleteByIndex(int index) {
        checkBounds(index);

        int result = arr[index];
        if (size - 1 - index >= 0)
            System.arraycopy(arr, index + 1, arr, index, size - 1 - index);
        size--;

        return result;
    }

    public void deleteAll() {
        size = 0;
        // Можно было бы удалить еще и массив arr = null;
        // Это было бы эффективнее с точки зрения памяти
        // Но раз уж массив дорос до какого-то размера один раз,
        // то также может дорасти и второй раз
        // На тот случай, что пользователь точно знает,
        // что массив уже таким большим не вырастет,
        // и его беспокоит слишком большой размер внутреннего массива,
        // можно сделать какой-нибудь
        // public void deleteAllWithTrunk(int targetLength) {
        // size = 0; arr = new int[targetLength]; }
    }

    @Override
    public String toString() {
        if (arr == null) return "null";
        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        while (true) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
            i++;
        }
    }

    public int find(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }

    public boolean hasValue(int value) {
        if (!isSorted)
            throw new RuntimeException("try the 'find' method");

        int l = 0;
        int r = size;
        int m;
        while (l < r) {
            m = (l + r) >> 1; // (l + r) / 2
            if (value == arr[m])
                return true;
            else if (value < arr[m])
                r = m;
            else
                l = m + 1;
        }
        return false;
    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Сложноность O((N^2)/2), она же O((N^2))
    public void sortBubble() {
        for (int i = 0; i < size; i++) {
            // Наверх пузырек выталкивает самый большой элемент массива
            // Нет необходимости итерировать по упорядоченной части массива
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(j, j + 1);
            }
        }
        isSorted = true;
    }

    // Сложноность O((N^2)/2), она же O((N^2))
    public void sortSelect() {
        for (int flag = 0; flag < size; flag++) {
            int cMin = flag;
            for (int rem = flag + 1; rem < size; rem++)
                if (arr[rem] < arr[cMin])
                    cMin = rem;
            swap(flag, cMin);
        }
        isSorted = true;
    }

    // Сложноность O((N^2))
    // Очень хорошо работает на частично отсортированных массивах
    // (в отличие от двух двугих, которым пофигу отсортированность исходного списка)
    // На уже отсортированном списке затраты будут O(N), другие 2 алгоритма будут лопатить данные впустую
    public void sortInsert() {
        for (int out = 1; out < size; out++) {
            int temp = arr[out];
            int in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
        isSorted = true;
    }

    // Сделал метод публичным - вдруг пригодится кому-то
    public int min() {
        if (arr == null)
            throw new RuntimeException("empty array");

        int min = arr[0];
        for (int i = 0; i < size; i++) {
            if (min > arr[i]) min = arr[i];
        }
        return min;
    }

    // Сделал метод публичным - вдруг пригодится кому-то
    public int max() {
        if (arr == null)
            throw new RuntimeException("empty array");

        int max = arr[0];
        for (int i = 0; i < size; i++) {
            if (max < arr[i]) max = arr[i];
        }
        return max;
    }

    // Сложноность O(N+K), где K равно max-min (т.е. разбросу значений)
    // Алгоритм весьма хорош для случаев, когда разброс значений
    // в массиве небольшой, а сами значения часто повторяются
    // Однако, если для примера взять исходный массив {0,10000000},
    // получим АДСКИ неадекватную трудоемкость и затраты памяти
    public void sortCounting() {
        int min = min();
        int max = max();

        int[] counter = new int[max - min + 1];

        for (int i = 0; i < size; i++) {
            counter[arr[i] - min]++;
        }

        int pointer = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > 0) {
                for (int j = 0; j < counter[i]; j++)
                    arr[pointer++] = i + min;
            }
        }
    }
}
