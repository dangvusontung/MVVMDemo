package sontung.dangvu.daggerdemo.model

data class Bag (val items : ArrayList<Item>) {
    fun addItem(item: Item){
        items.add(item)
    }
}