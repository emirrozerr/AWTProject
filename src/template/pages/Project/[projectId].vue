<script setup lang="ts">
import { ref } from 'vue';
import Board from "~/components/view/Board.vue";
import TodoView from "~/components/view/TodoView.vue";

definePageMeta({
  middleware: 'auth'
});
const activeTab = ref(0);
const route = useRoute();
const projectId = Array.isArray(route.params.projectId) ? route.params.projectId[0] : route.params.projectId; // Get the project ID from the route


const { fetchTasks } = useTasks();

onMounted(() => {
  fetchTasks(Number(projectId));
});

</script>

<template>
  <v-row>
    <v-col cols="12">
      <v-tabs v-model="activeTab">
        <v-tab>Kanban Board</v-tab>
        <v-tab>To-do List</v-tab>
      </v-tabs>

      <div v-show="activeTab === 0">
        <Board />
      </div>
      <div v-show="activeTab === 1">
        <TodoView />
      </div>
    </v-col>
  </v-row>
</template>

<style scoped lang="scss">
.withbg {
  background-color: #f7f9fc;
}
</style>
