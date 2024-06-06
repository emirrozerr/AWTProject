<template>
  <v-card>
    <v-card-title>Calendar</v-card-title>
    <v-divider></v-divider>
    <v-card-text>
      <v-menu
        v-model="menu"
        :close-on-content-click="false"
        transition="scale-transition"
        offset-y
        max-width="290px"
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="date"
            label="Select Date"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" @input="updateDate"></v-date-picker>
      </v-menu>
    </v-card-text>
  </v-card>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps({
  selectedMedicine: {
    type: Object,
    default: null
  }
});

const emit = defineEmits(['select-date']);

const menu = ref(false);
const date = ref(new Date().toISOString().substr(0, 10));

const updateDate = (newDate) => {
  date.value = newDate;
  emit('select-date', newDate);
};
</script>
