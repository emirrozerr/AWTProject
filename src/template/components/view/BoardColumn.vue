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
        :animation="250"
        ghost-class="ghost-card"
        group="tasks"
        @change="onChange"
      >
        <template #item="{ element }">
          <Task :task="element" />
        </template>
      </draggable>
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
import { ref } from 'vue';
import draggable from 'vuedraggable';
import Task from '~/components/view/Task.vue';
import { PlusIcon } from 'vue-tabler-icons';
import { type Task as TaskType, type TaskDTO } from '@/types/Task';
import { VDateInput } from 'vuetify/labs/VDateInput'

const route = useRoute();
const projectId = Array.isArray(route.params.projectId) ? route.params.projectId[0] : route.params.projectId; // Get the project ID from the route

interface Props {
  title: string;
  tasks: TaskType[];
  status: string;
}

const props = defineProps<Props>();

const emit = defineEmits<{
  (e: "update-status", taskId: number, newStatus: "todo" | "doing" | "done"): void;
}>();

const showAddTask = ref(false);
const newTaskTitle = ref('');
const newTaskDescription = ref('');
const newTaskDueDate = ref();

const { addTask } = useTasks();

const onChange = (event: { added?: { element: TaskType } }) => {
  const { added } = event;
  if (added && added.element) {
    emit('update-status', added.element.id, props.status as 'todo' | 'doing' | 'done');
    console.log(added)
  }
};

const addTaskEvent = () => {
  if(newTaskDueDate)
      console.log(newTaskDueDate);

  if (newTaskTitle.value.trim()) {

    const newTask: TaskDTO = {
      title: newTaskTitle.value,
      description: newTaskDescription.value,
      dueDate: newTaskDueDate.value,
      priority: 'High',
      status: props.status as 'todo' | 'doing' | 'done',
      projectId: Number(projectId)
    };

    addTask(newTask);
    newTaskTitle.value = '';
    newTaskDescription.value = '';
    newTaskDueDate.value = '';
    showAddTask.value = false;
    // emit('update-status', null as any, null as any); // Trigger parent to fetch tasks again
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
