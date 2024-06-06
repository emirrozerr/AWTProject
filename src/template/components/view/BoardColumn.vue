<template>
  <v-card>
    <v-card-title class="d-flex justify-space-between">
      {{ title }}
      <v-btn icon class="ml-auto " @click="showAddTask = true" small>
        <PlusIcon size="20" stroke-width="1.5"></PlusIcon>
      </v-btn>
    </v-card-title>
    <v-divider></v-divider>
    <v-card-text>
      <draggable 
        :list="tasks" 
        item-key="id" 
        :animation="200" 
        ghost-class="ghost-card" 
        group="tasks" 
        @change="onChange"
      >
        <template #item="{ element }">
          <Task :task="element" @update-status="updateTaskStatus" />
        </template>
      </draggable>
    </v-card-text>
    <v-dialog v-model="showAddTask" max-width="500px">
      <v-card>
        <v-card-title>Add New Task</v-card-title>
        <v-card-text>
          <v-text-field v-model="newTaskTitle" label="Task Title"></v-text-field>
          <v-text-field v-model="newTaskDescription" label="Task Description"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="addTask">Add</v-btn>
          <v-btn @click="showAddTask = false">Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script setup>
import { ref } from 'vue';
import draggable from 'vuedraggable';
import Task from '~/components/view/Task.vue';
import {PlusIcon} from 'vue-tabler-icons'

const props = defineProps({
  title: String,
  tasks: {
    type: Array,
    default: () => []
  },
  status: {
    type: String,
    required: true
  }
});

const { addTaskToList } = useTasks();

const emit = defineEmits(['update-status']);
const showAddTask = ref(false);
const newTaskTitle = ref('');
const newTaskDescription = ref('');

const onChange = (event) => {
  const { added } = event;
  if (added && added.element) {
    emit('update-status', added.element.id, props.status);
  }
};

const updateTaskStatus = (taskId, newStatus) => {
  emit('update-status', taskId, newStatus);
};

const addTask = () => {
  if (newTaskTitle.value.trim()) {
    addTaskToList(newTaskTitle.value, props.status,newTaskDescription.value,);
    newTaskTitle.value = '';
    newTaskDescription.value = '';
    showAddTask.value = false;
  }
};
</script>

<style scoped>
.ghost-card {
  opacity: 0.5;
  background: #F7FAFC;
  border: 1px solid #4299e1;
}
</style>
