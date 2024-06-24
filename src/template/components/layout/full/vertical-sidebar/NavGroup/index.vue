<script setup lang="ts">
import {ref} from "vue";

const router = useRouter()

const isModalOpen = ref(false);

const emit = defineEmits(['update:isModalOpen']);

const openModal = () => {
  //   router.push('/settings')
  isModalOpen.value = true;
  emit('update:isModalOpen', isModalOpen.value);
};

const closeModal = () => {
  isModalOpen.value = false;
  emit('update:isModalOpen', isModalOpen.value);
};

const props = defineProps<{ item: Object }>();
</script>

<template>

  <v-list-subheader color="darkText" class="smallCap text-uppercase text-subtitle-2 mt-5 font-weight-bold">
    {{props.item.header}}
    <v-icon v-if="props.item.editButton" icon="mdi-cog" class="ml-5" @click="openModal" />
  </v-list-subheader>

</template>

<style scoped>
</style>
