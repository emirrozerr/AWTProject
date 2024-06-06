<template>
  <v-card>
    <v-card-title class="d-flex justify-space-between">Medicine List
      <v-btn icon class="ml-auto " @click="addMedicine" small>
        <PlusIcon size="20" stroke-width="1.5"></PlusIcon>
      </v-btn>
    </v-card-title>
    <v-divider></v-divider>
    <v-card-text>
      <v-list>
        <v-list-item
          v-for="medicine in medicines"
          :key="medicine.id"
          @click="selectMedicine(medicine)"
        >
          
          <v-list-item-title>{{ medicine.name }}</v-list-item-title>
          <v-list-item-subtitle>{{ medicine.dose }}</v-list-item-subtitle>
        
        </v-list-item>
      </v-list>
    </v-card-text>
  </v-card>
</template>

<script setup>
import { ref } from 'vue';

const medicines = ref([
  { id: 1, name: 'Medicine X', dose: '10mg', instructions: 'One capsule every morning' }
]);

const emit = defineEmits(['select-medicine']);

const selectMedicine = (medicine) => {
  emit('select-medicine', medicine);
};

const addMedicine = () => {
  const newMedicine = { id: Date.now(), name: 'New Medicine', dose: '', instructions: '' };
  medicines.value.push(newMedicine);
  emit('select-medicine', newMedicine);
};
</script>
