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
            :label="task.title"
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
            :label="task.title"
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
          <v-text-field v-model="newTaskDescription" label="Task Description"></v-text-field>
          <v-date-input
              v-model="newTaskDueDate"
              label="Due Date"
              prepend-icon=""
              persistent-placeholder
          ></v-date-input>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="addTaskEvent">Add</v-btn>
          <v-btn @click="showAddTask = false">Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import type {TaskDTO} from "~/types/Task";
import { VDateInput } from 'vuetify/labs/VDateInput'

const route = useRoute();
const projectId = Array.isArray(route.params.projectId) ? route.params.projectId[0] : route.params.projectId; // Get the project ID from the route


const { tasks, updateTaskStatus, addTask } = useTasks();

const showAddTask = ref(false);
const newTaskTitle = ref('');
const newTaskDescription = ref('');
const newTaskDueDate = ref();

const activeTasks = computed(() => tasks.filter(task => task.status === 'todo' || task.status === 'doing'));
const doneTasks = computed(() => tasks.filter(task => task.status === 'done'));

const toggleTaskStatus = (task: any) => {
  updateTaskStatus(task.id, 'done');
};

const addTaskEvent = () => {
  if (newTaskTitle.value.trim()) {

    const newTask: TaskDTO = {
      title: newTaskTitle.value,
      description: newTaskDescription.value,
      dueDate: newTaskDueDate.value,
      priority: 'High',
      status: 'todo',
      projectId: Number(projectId)
    };

    addTask(newTask);
    newTaskTitle.value = '';
    newTaskDescription.value = '';
    newTaskDueDate.value = '';
    showAddTask.value = false;
  }
};
</script>

<style scoped>
.task-item.done {
  text-decoration: line-through;
}
</style>
