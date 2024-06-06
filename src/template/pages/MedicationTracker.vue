<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card elevation="10" class="withbg">
          <v-card-title>Medication Tracker</v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <!-- Medication List -->
              <v-col cols="12" md="6">
                <MedicationList @select-medicine="selectMedicine" />
              </v-col>
              <!-- Calendar and Summary only when a medicine is selected -->
              <v-col v-if="selectedMedicine" cols="12" md="6">
                <CalendarSummary :selectedMedicine="selectedMedicine" @select-date="selectDate" />
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref } from 'vue';
import MedicationList from '@/components/view/MedicationList.vue';
import CalendarSummary from '@/components/view/CalendarSummary.vue';

const selectedMedicine = ref(null);
const selectedDate = ref(new Date().toISOString().substr(0, 10));

const selectMedicine = (medicine) => {
  selectedMedicine.value = medicine;
};

const selectDate = (date) => {
  selectedDate.value = date;
};
</script>

<style scoped lang="scss">
.withbg {
  background-color: #f7f9fc;
}
</style>
