<template>
  <v-card elevation="10" class="withbg">
    <v-card-title>
      To-do List
      <v-btn class="ml-auto" @click="showAddTask = true" small>Add Task</v-btn>
    </v-card-title>
    <v-divider></v-divider>
    <v-card-text>
      <v-list>
        <v-list-item
          v-for="task in activeTasks"
          :key="task.id"
          class="task-item"
        >
          <v-checkbox
            v-model="task.status"
            :value="'done'"
            :label="task.name"
            @change="() => toggleTaskStatus(task)"
          ></v-checkbox>
        </v-list-item>
      </v-list>
      <v-divider></v-divider>
      <v-card-title>Finished Tasks</v-card-title>
      <v-list>
        <v-list-item
          v-for="task in doneTasks"
          :key="task.id"
          class="task-item done"
        >
          <v-checkbox
            v-model="task.status"
            :value="'done'"
            :label="task.name"
            @change="() => toggleTaskStatus(task)"
          ></v-checkbox>
        </v-list-item>
      </v-list>
    </v-card-text>
    <v-dialog v-model="showAddTask" max-width="500px">
      <v-card>
        <v-card-title>Add New Task</v-card-title>
        <v-card-text>
          <v-text-field v-model="newTaskTitle" label="Task Title"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="addTask">Add</v-btn>
          <v-btn @click="showAddTask = false">Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';

const { tasks, updateTaskStatus, addTaskToList } = useTasks();

const showAddTask = ref(false);
const newTaskTitle = ref('');

const activeTasks = computed(() => tasks.filter(task => task.status === 'todo' || task.status === 'doing'));
const doneTasks = computed(() => tasks.filter(task => task.status === 'done'));

const toggleTaskStatus = (task: any) => {
  updateTaskStatus(task.id, 'done');
};

const addTask = () => {
  if (newTaskTitle.value.trim()) {
    addTaskToList(newTaskTitle.value, 'todo');
    newTaskTitle.value = '';
    showAddTask.value = false;
  }
};
</script>

<style scoped>
.task-item.done {
  text-decoration: line-through;
}
</style>
