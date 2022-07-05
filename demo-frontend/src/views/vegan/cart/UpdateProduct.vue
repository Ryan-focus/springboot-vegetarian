<script setup>
import { ref, reactive, computed, toRefs } from "vue";
</script>

<template>
  <form @sumbit.prevent>
    <!-- 商品名 -->
    <div class="mb-4">
      <label class="form-label" for="example-ltf-email">商品名稱</label>
      <input
        type="text"
        class="form-control"
        id="productName"
        name="productName"
      />
    </div>

    <!-- 商品分類開始 -->
    <div class="mb-4">
      <label class="form-label" for="example-select">選擇商品分類</label>
      <select
        class="form-select"
        id="example-select"
        name="example-select"
        v-model="category"
      >
        <option selected>按我選擇</option>
        <option value="生鮮">生鮮</option>
        <option value="食品">食品</option>
        <option value="寵物">寵物</option>
        <option value="居家百貨">居家百貨</option>
        <option value="飾品">飾品</option>
        <option value="保健">保健</option>
        <option value="書籍影音">書籍影音</option>
        <option value="美妝保養">美妝保養</option>
        <option value="量販批發">量販批發</option>
      </select>
    </div>
    <!-- 素食種類開始 -->
    <div class="mb-4">
      <label class="form-label" for="example-select">選擇素食種類</label>
      <select
        class="form-select"
        id="example-select"
        name="example-select"
        v-model="veganCategory"
      >
        <option selected>按我選擇</option>
        <option value="全素">全素</option>
        <option value="蛋素">蛋素</option>
        <option value="奶素">奶素</option>
        <option value="蛋奶素">蛋奶素</option>
        <option value="五辛素">五辛素</option>
        <option value="VEGAN">VEGAN</option>
        <option value="書籍影音">書籍影音</option>
        <option value="美妝保養">美妝保養</option>
        <option value="量販批發">量販批發</option>
      </select>
    </div>
    <!-- 價格 -->

    <div class="mb-4">
      <label class="form-label" for="example-ltf-email">價格</label>
      <input
        type="number"
        class="form-control"
        id="productPrice"
        name="productPrice"
      />
    </div>

    <!-- 庫存 -->
    <div class="mb-4">
      <label class="form-label" for="example-ltf-email">庫存</label>
      <input type="stock" class="form-control" id="stock" name="stock" />
    </div>

    <div class="mb-4">
      <button type="submit" class="btn btn-primary">送出更新</button>
    </div>
  </form>
</template>
