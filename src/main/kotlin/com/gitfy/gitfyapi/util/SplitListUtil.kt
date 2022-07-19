package com.gitfy.gitfyapi.util

object SplitListUtil {

    /**
     * 拆分列表
     *
     * @param T 泛型对象
     * @param list 需要拆分的集合
     * @param length 每个子集合的元素个数
     * @return 返回拆分后的各个集合组成的列表
     */
    fun <T> split(list: List<T>, length: Int): List<List<T>> {
        if (list.isEmpty() || length <= 0) {
            return listOf()
        }
        val lists: MutableList<List<T>> = mutableListOf()
        val size = list.size
        val flag = size % length != 0
        val groupSize = size / length + if (flag) 1 else 0
        for (i in 0 until groupSize) {
            val group = if (i == groupSize - 1 && flag) list.subList(i * length, size)
            else list.subList(i * length, i * length + length)
            lists.add(group)
        }
        return lists
    }

}