import java.util.ArrayList;

import Exceptions.DataStorageEmptyException;
import Exceptions.DataStorageOverflowException;

public class DataStorage<T> {
	private int maxSize = 10;
	private ArrayList<T> storage;

	public DataStorage(int maxSize) {
		this.maxSize = maxSize;
		storage = new ArrayList<T>(maxSize);
	}

	public DataStorage() {
		storage = new ArrayList<T>(maxSize);
	}

	public void Add(T value) throws DataStorageOverflowException {
		if (storage.size() == maxSize) {
			throw new DataStorageOverflowException();
		}

		storage.add(value);
	}

	public void Remove() throws DataStorageEmptyException {
		if (storage.size() == 0) {
			throw new DataStorageEmptyException();
		}

		storage.remove(storage.size() - 1);
	}

	public int Find(T value) throws DataStorageEmptyException {
		if (storage.size() == 0) {
			throw new DataStorageEmptyException();
		}

		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i) == value) {
				return i;
			}
		}

		return -1;
	}
	
	public void Print() {
		for (T value : storage) {
			System.out.println(value);
		}
	}
}
