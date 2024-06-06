<template>
  <div>
    <v-card @click="openModal" class="ma-3">
      <v-card-title>{{ task.name }}</v-card-title>
    </v-card>

    <v-dialog v-model="isModalOpen" max-width="600px">
      <v-card>
        <v-card-title class="d-flex justify-space-between">
          <span>{{ task.name }}</span>
          <v-btn icon @click="closeModal">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>

        <v-card-text>
          <v-text-field label="Description" v-model="task.description" multiline></v-text-field>
          <v-row>
            <v-col>
              <v-menu
                v-model="datePickerMenu"
                :close-on-content-click="false"
                transition="scale-transition"
                offset-y
                max-width="290px"
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="task.dueDate"
                    label="Due date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="task.dueDate" @input="datePickerMenu = false"></v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <v-select
            label="Status"
            :items="['todo', 'doing', 'done']"
            v-model="task.status"
          ></v-select>
        </v-card-text>

        <v-card-actions>
          <v-btn color="primary" @click="saveTask">Save</v-btn>
          <v-btn text @click="closeModal">Cancel</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';

const props = defineProps({
  task: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['update-status']);

const isModalOpen = ref(false);
const datePickerMenu = ref(false);

const openModal = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
};

const saveTask = () => {
  emit('update-status', props.task.id, props.task.status);
  closeModal();
};
</script>

<style scoped>
/* Add any specific styles for the Task component here */
</style>
