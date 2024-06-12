<template>
  <div>
    <v-card @click="openModal" class="ma-3 task-card">
      <v-card-title>{{ task.title }}</v-card-title>
    </v-card>

    <v-dialog v-model="isModalOpen" max-width="600px">
      <v-card>
        <v-card-title class="d-flex justify-space-between">
          {{ "Task" }}
          <v-btn icon @click="closeModal">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>

        <v-card-text>
          <v-text-field label="Title" v-model="task.title"></v-text-field>
          <v-text-field label="Description" v-model="task.description" multiline></v-text-field>
          <v-date-input
              v-model="dueDateHolder"
              label="Due Date"
              prepend-icon=""
              persistent-placeholder
          ></v-date-input>

          <v-select
            label="Status"
            :items="['todo', 'doing', 'done']"
            v-model="taskStatusHolder"
          ></v-select>
        </v-card-text>

        <v-card-actions>
          <v-btn color="primary" @click="updateTaskEvent">Save</v-btn>
          <v-btn @click="closeModal">Cancel</v-btn>
          <v-btn color="red" @click="deleteTaskEvent">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import type { Task,TaskDTO } from '@/types/Task';
import { VDateInput } from 'vuetify/labs/VDateInput'

interface Props {
  task: Task;
}

const props = defineProps<Props>();

const { deleteTask,updateTask } = useTasks();

const isModalOpen = ref(false);
const dueDateHolder = ref(props.task.dueDate)
const taskStatusHolder = ref(props.task.status);

const openModal = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
};

const updateTaskEvent = () => {
  //TODO some validations and error handling

  const updatedTask: TaskDTO = {
    id: props.task.id,
    title: props.task.title,
    description: props.task.description,
    dueDate: dueDateHolder.value,
    priority: props.task.priority,
    status: taskStatusHolder.value as 'todo' | 'doing' | 'done',
  };
  updateTask(updatedTask);
  closeModal();
};

const deleteTaskEvent = () => {
  //TODO show confirmation dialog
  deleteTask(props.task.id)
};

</script>

<style scoped>
.task-title {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 100%;
  display: block;
}
.task-card{
  max-width: 100%;
  display: block;
}
</style>
