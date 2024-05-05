package school.sptech.apicodando.service.arrayService;

import java.util.List;

public class Array<T> {
    private T[] array;
    private int interator;

    public Array(int interator) {
        this.array = (T[]) new Object[interator];
        this.interator = interator;
    }

    // Adicionar elemento na lista recebendo um elemento.
    public void add(T e) {
        if (interator == array.length) {
            verifyArray();
        }
        array[interator++] = e;
    }

    public void addAll(List<T> list){
        for (int i = 0; i < interator; i++) {

            if(interator == array.length){
                verifyArray();
            }

            array[i] = list.get(i);
        }
    }

    // Adicionar um elemento na lista recebendo o elemento, e a posição que deseja.
    // ARRUMAR ESSE METODO.
    public void add(int index, T e) {
        if (interator == array.length) {
            verifyArray();
        }

        if (!indexExist(index)) {
            throw new IllegalStateException("Index not exist.");
        } else {
            array[index] = e;
        }
    }


    // Busca um elemento pelo index da lista/
    public T findByIndex(int index) {
        if (!indexExist(index)) {
            return null;
        }
        return array[index];
    }

    /*
        Busca o index atraves de um elemento
        Quando esse elemento existe na lista é retornado o seu index.
        Se ele não existe na lista é retornado -1.
     */

    public int findByElement(T e) {
        for (int i = 0; i < interator; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /*
        Atualiza um elemento recebendo seu indice, e o novo elemento.
     */

    public void setElement(int index, T e) {
        if (indexExist(index)) {
            array[index] = e;
        }
    }

    // Remove um elemento pelo seu indice
    // Caso seu indice não seja encontrado retornará false, se não retornará true.

    public boolean removeByIndex(int index) {
        if (indexExist(index)) {
            for (int i = index; i < interator - 1; i++) {
                array[i] = array[i + 1];
            }
            interator--;
            return true;
        }
        return false;
    }

    // Remove um elemento recebendo ele como parametro.

    public boolean removeByElement(T e) {
        return removeByIndex(findByElement(e));
    }

    // Verifica se a lista está vazia.

    public boolean isEmpty() {
        if (interator == 0) {
            return true;
        }
        return false;
    }

    // Limpar a lista.

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    // Retorna o tamanho do array;

    public int size(){
        return interator;
    }

    // Numero de ocorrencias de um objeto.

    public int numberOccurrences(T e){
        int o = 0;
        for (int i = 0; i < interator; i++) {
            if(e.equals(array[i])){
              o++;
            }
        }
        return o;
    }

    // Verifica se o index existe dentro do array.

    public boolean indexExist(int index) {
        if (index < 0 || index > interator) {
            return false;
        }
        return true;
    }

    public void verifyArray() {
        int newSize = interator + 10;
        T[] arrayAux = (T[]) new Object[newSize];

        for (int i = 0; i < interator; i++) {
            arrayAux[i] = array[i];
        }

        array = arrayAux;
    }

}

