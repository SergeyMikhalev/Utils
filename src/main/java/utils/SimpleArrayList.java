package utils;

import java.util.*;

public class SimpleArrayList<E> implements List<E> {

    public static final int DEFAULT_INITIAL_CAPACITY = 4;
    private int size;
    private int modCount;
    private Object[] data;

    public SimpleArrayList() {
        data = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public SimpleArrayList(int initSize) {
        data = new Object[initSize];
    }

    private void grow() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    /**
     * Метод получения размера списка
     *
     * @return Возвращает количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод определения пуст ли список
     *
     * @return Возвращает true если список пуст, в противном случае
     * возвращает false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод определения наличия объекта в списке
     *
     * @param o объект, который ищется в списке
     * @return Возвращает true, если объект содержится в списке,
     * в противном случае возвращает false
     */
    @Override
    public boolean contains(Object o) {
        boolean result = false;
        for (int i = 0; i <= size; i++) {
            if (Objects.equals(o, data[i])) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает Итератор по списку
     *
     * @return Возвращает итератор по списку
     */
    @Override
    public Iterator<E> iterator() {
        return new InnerIterator<>();
    }

    /**
     * Итератор по SimpleArrayList
     *
     * @param <E> Тип данных, хранимых в коллекции
     *            по которой осуществляется итерирование
     */
    private class InnerIterator<E> implements Iterator<E> {
        private int pointer = 0;
        private int expModCount = modCount;

        /**
         * Метод для определения есть ли в списке ещё элементы
         *
         * @return возвращает true если в списке остались элементы
         * через которые ещё не прошёл итератор
         */
        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        /**
         * Метод для получения элемента списка
         *
         * @return возвращает следующий элемент списка
         * @throws NoSuchElementException          если в списке нет следующего элемента
         * @throws ConcurrentModificationException если после создания итератора список был изменён.
         */
        @Override
        public E next() {
            checkMod();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) data[pointer++];
        }

        private void checkMod() {
            if (expModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * Метод добавления элемента в список
     *
     * @param e Элемент, добавляемый в конец списка
     * @return возвращает true, если элемент был добавлен (т.е. всегда,
     * пока есть свободная память)
     */
    @Override
    public boolean add(E e) {
        modCount++;
        if (size == data.length) {
            grow();
        }
        data[size++] = e;
        return true;
    }

    /**
     * Метод удаления объекта из списка
     *
     * @param o объект, который надо удалить из списка
     * @return возвращает true если объект был в списке, иначе возвращает false
     */
    @Override
    public boolean remove(Object o) {
        boolean result = false;
        int index = indexOf(o);
        if (index != -1) {
            modCount++;
            result = true;
            remove(index);
        }
        return result;
    }

    /**
     * Метод проверяет, что все элементы коллекции c присутствуют в списке
     *
     * @param c коллекция, присутствие в списке элементов которой проверяется
     * @return возвращает true если все элементы коллекции c представлены в списке,
     * инча возвращает false
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        Object[] elements = c.toArray();
        for (int i = 0; i < elements.length; i++) {
            if (!contains(elements[i])) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод добавляет все элементы коллекции с в конец списка
     *
     * @param c коллекция с элементами для добавления в список
     * @return возвращает true если в коллекцию добавлено хотя
     * бы один элемент. Иначе возвращает false
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] arr = c.toArray();
        if (arr.length == 0) {
            return false;
        }
        modCount++;
        while (arr.length > data.length - size) {
            grow();
        }
        System.arraycopy(arr, 0, data, size, arr.length);
        size += arr.length;
        return true;
    }

    /**
     * Метод добавляет все элементы коллекции с в список,
     * вставляя их начиная с индекса index
     *
     * @param index позиция в списке с которой начинается вставка
     * @param c     коллекция элементов, которые будут вставлены
     * @return возвращает true если в коллекцию добавлено хотя
     * бы один элемент. Иначе возвращает false
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Objects.checkIndex(index, size + 1);
        Object[] arr = c.toArray();
        if (arr.length == 0) {
            return false;
        }
        modCount++;
        while (arr.length > data.length - size) {
            grow();
        }
        if (index < size) {
            System.arraycopy(data, index, data, index + arr.length, size-index);
        }
        System.arraycopy(arr, 0, data, index, arr.length);
        size += arr.length;
        return true;
    }

    /**
     * Метод удаляет все элементы коллекции с, которые были в списке
     *
     * @param c коллекция элементов, которые подлежат удалению из списка
     * @return возвращает true если в результате из списка был удален
     * хотя бы один элемент коллекции
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        boolean result = false;
        Iterator it = c.iterator();

        while (it.hasNext()) {
            result = result | remove(it.next());
        }

        return result;
    }

    /**
     * Метод оставляет в списке только те элементы, которые есть
     * в коллекции c
     *
     * @return возвращает true если в результате применения
     * операции список был изменен.
     * @throws NullPointerException если коллекция c==null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (null == c) {
            throw new NullPointerException();
        }

        Object[] newData = new Object[data.length];
        int i = 0;
        for (int j = 0; j < size; j++) {
            if (c.contains(data[j])) {
                newData[i++] = data[j];
            }
        }
        boolean result = (i != size);
        data = newData;
        size = i;
        return result;
    }

    /**
     * Метод удаляет все элементы из списка
     */
    @Override
    public void clear() {
        if (size > 0) {
            modCount++;
        }
        while (size > 0) {
            data[--size] = null;
        }
        data[size] = null;
    }

    /**
     * Метод получения элемента списка по индексу
     *
     * @param index индекс - номер элемента в списке, счет ведется с 0
     * @return возвращает элемент по указанной позиции, если индекс
     * находится за пределами списка выбрасывает IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) data[index];
    }

    /**
     * Метод замены элемента по индексу в списке
     *
     * @param index   номер элемента в списке, который надо заменить
     * @param element элемента на который надо заменить элемент в списке
     * @return возвращает элемент списка, который был заменён. В случае
     * неверно заданного номера заменяемого элемента выбрасывает
     * IndexOutOfBoundsException
     */
    @Override
    public E set(int index, E element) {
        E result = get(index);
        modCount++;
        data[index] = element;
        return result;
    }

    /**
     * Метод вставляет элемент на указанную позицию в списке
     *
     * @param index   индекс суда вставляется элемент
     * @param element элемент для вставки
     *                Если индекс выходит за границы списка выбрасывается исключение
     *                IndexOutOfBoundsException
     */
    @Override
    public void add(int index, E element) {
        Objects.checkIndex(index, size + 1);
        modCount++;
        if (size == data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size += 1;
    }

    /**
     * Метод удаления элемента из списка по индексу
     *
     * @param index позиция элемента в списке, который надо удалить
     * @return возвращает true если элемент был удален.
     * Фактически, если не возвращает true, выбрасывает
     * IndexOutOfBoundsException т.к. указан неверный номер
     * элемента
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        final int newSize = size - 1;
        if (index < newSize) {
            System.arraycopy(data, index + 1, data, index, newSize - index);
        }
        E result = (E) data[size = newSize];
        data[size] = null;
        return result;
    }

    /**
     * Метод для нахождения индекса первого вхождения элемента в список
     *
     * @param o элемент, который ищем в списке
     * @return возвращает индекс первого найденного вхождения элемента в список.
     * Если элемент не был найден в списке возвращает -1
     */
    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Метод для нахождения индекса последнего вхождения элемента в список
     *
     * @param o элемент, который ищем в списке
     * @return возвращает индекс последнего найденного вхождения элемента в список.
     * Если элемент не был найден в списке возвращает -1
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(0, data[i])) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Метод сортирует список при помощи передаваемого ему объекта-компаратора
     *
     * @param c - компаратор, сравнивающий элементы списка для сортировки
     */
    @Override
    public void sort(Comparator<? super E> c) {
        innerQuickSort(0, size - 1, c);
    }

    private void innerQuickSort(int begin, int end, Comparator<? super E> comparator) {
        if (begin < end) {
            int partitionIndex = partition(begin, end, comparator);

            innerQuickSort(begin, partitionIndex - 1, comparator);
            innerQuickSort(partitionIndex + 1, end, comparator);
        }
    }

    private int partition(int begin, int end, Comparator<? super E> comparator) {
        int pivotPos = end;
        Object pivot = data[end];

        Comparator c = (Comparator) comparator;

        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (c.compare(data[j], pivot) <= 0) {
                i++;
                if (i != j) {
                    swap(i, j);
                }
            }
        }
        swap(i + 1, pivotPos);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        if (size > 0) {
            while (i < size) {
                sb.append(data[i].toString());
                sb.append(", ");
                i++;
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
