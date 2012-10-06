package ods;

import java.util.*;

public class HashTable<T> extends AbstractCollection<T> {
	List<T>[] t;
	int d;
	int n;
	int z;
	protected static final int w = 32;
	
        //Tao bang bam moi
	public HashTable() {
		d = 0;
		t = allocTable(1<<d);
		Random r = new Random();
		z = r.nextInt() | 1; // so nguyen le ngau nhien
	}
	@SuppressWarnings({"unchecked"})
	protected List<T>[] allocTable(int s) {
		List<T>[] tab = new ArrayList[s];
		for (int i = 0; i < s; i++) {
			tab[i] = new ArrayList<T>();
		}
		return tab;
	}
	
	/**
	 * Thay doi kich thuoc bang bam len 2^d 
         * 
         * @param d 
         */
	protected void resize(int d) {
		this.d = d;
		List<T>[] oldTable = t;
		t = allocTable(1<<d);
		for (int i = 0; i < oldTable.length; i++) {
			for (T x : oldTable[i]) {
				add(x);
			}
		}
	}
	
	/**
	 * Gap doi kich thuoc bang bam
	 */
	protected void grow() {
		resize(d+1);
	}
	
	/**
	 * giam kich thuoc
	 */
	protected void shrink() {
		resize(d-1);
	}
    @Override
	public int size() {
		return n;
	}

	/**
         * @param x 
         * @return ((x.hashCode() * z) mod 2^w) div 2^(w-d)
	 */
	protected final int hash(Object x) {
		return (z * x.hashCode()) >>> (w-d);
	}
    @Override
	public boolean add(T x) {
		if (n+1 > t.length)
			grow();
		t[hash(x)].add(x);
		n++;
		return true;
	}
	public int removeAll(Object x) {
		int r = 0;
		Iterator<T> it = t[hash(x)].iterator();
		while (it.hasNext()) {
			T y = it.next();
			if (y.equals(x)) {
				it.remove();
				n--;
				r++;
			}
		}
		return r;
	}

	public T removeOne(Object x) {
		Iterator<T> it = t[hash(x)].iterator();
		while (it.hasNext()) {
			T y = it.next();
			if (y.equals(x)) {
				it.remove();
				n--;
				return y;
			}
		}
		return null;
	}

    @Override
	public boolean remove(Object x) {
		return removeOne(x) != null;
	}
	public T find(Object x) {
		for (T y : t[hash(x)])
			if (y.equals(x))
				return y;
		return null;
	}
	
	public List<T> findAll(Object x) {
		List<T> l = new LinkedList<T>();
		int i = (x.hashCode() * z) >>> (w-d);
		for (T y : t[i]) {
			if (y.equals(x)) {
				l.add(y);
			}
		}
		return l;
	}

    @Override
	public Iterator<T> iterator() {
		class IT implements Iterator<T> {
			int i, j;
			int ilast, jlast;
			IT() {
				i = 0;
				j = 0;
				while (i < t.length && t[i].isEmpty())
					i++;
			}
			protected void jumpToNext() {
				while (i < t.length && j + 1 > t[i].size()) {
					j = 0;
					i++;
				}
			}
            @Override
			public boolean hasNext() {
				return i < t.length;
			}
            @Override
			public T next() {
				ilast = i;
				jlast = j; 
				T x =  t[i].get(j);
				j++;
				jumpToNext();
				return x;
			}
            @Override
			public void remove() {
				HashTable.this.remove(t[ilast].get(jlast));
			}		
		}
		return new IT();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 100000;
		HashTable<Integer> t = new HashTable<Integer>();
		for (int i = 0; i < n; i++) {
			t.add(i*2);
		}
		for (int i = 0; i < 2*n; i++) {
			Integer x = t.find(i);
			if (i % 2 == 0) {
				assert(x.intValue() == i);
			} else {
				assert(x == null);
			}
		}
	}

}
