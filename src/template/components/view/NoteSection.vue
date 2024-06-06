<template>
  <v-card>
    <v-card-title>Note</v-card-title>
    <v-divider></v-divider>
    <v-card-text>
      <v-textarea
        v-model="note"
        label="(e.g How do you feel?, Any side effects?)"
        rows="5"
        outlined
      ></v-textarea>
    </v-card-text>
  </v-card>
</template>

<script setup>
import { ref, watch } from 'vue';

const props = defineProps({
  selectedMedicine: {
    type: Object,
    default: null
  },
  selectedDate: {
    type: String,
    default: new Date().toISOString().substr(0, 10)
  }
});

const note = ref('');

watch([() => props.selectedMedicine, () => props.selectedDate], ([newMedicine, newDate]) => {
  if (newMedicine && newDate) {
    // Load note for the selected medicine and date
    // For example:
    note.value = '';  // Fetch the note from your data source
  }
});
</script>
