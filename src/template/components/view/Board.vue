<template>
  <v-container>
    <v-row>
      <v-col>
        <BoardColumn title="To Do" :tasks="todoTasks" status="todo" @update-status="updateStatus" />
      </v-col>
      <v-col>
        <BoardColumn title="Doing" :tasks="doingTasks" status="doing" @update-status="updateStatus" />
      </v-col>
      <v-col>
        <BoardColumn title="Done" :tasks="doneTasks" status="done" @update-status="updateStatus" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
import BoardColumn from '~/components/view/BoardColumn.vue';

const { updateTaskStatus, todoTasks, doingTasks ,doneTasks } = useTasks();

const updateStatus = (taskId: number, newStatus: 'todo' | 'doing' | 'done') => {
  updateTaskStatus(taskId, newStatus);
};

</script>
