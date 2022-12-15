package utils;

import java.util.*;

public class SimpleArrayList<E> implements List<E> {

    public static final int DEFAULT_INITIAL_CAPACITY = 10;
    private int size;
    private int modCount;
    private Object[] data;

    public SimpleArrayList() {
        data = new Object[DEFAULT_INITIAL_CAPACITY];
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
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
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
        int index = indexOf(0);
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
        Objects.checkIndex(index, size);
        Object[] arr = c.toArray();
        if (arr.length == 0) {
            return false;
        }
        modCount++;
        while (arr.length > data.length - size) {
            grow();
        }
        System.arraycopy(data, index, data, index + arr.length, arr.length);
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
     * Метод не реализован, выбрасывает UnsupportedOperationException
     *
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
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
        Objects.checkIndex(index, size);
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
            if (Objects.equals(0, data[i])) {
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
}
