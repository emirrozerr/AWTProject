<script setup>
import Icon from '../Icon.vue';
const props = defineProps({ item: Object, level: Number });
</script>

<template>
  <template v-if="item.collapse">
    <v-expansion-panels>
      <v-expansion-panel>
          <v-list-item
              class="pa-0 ma-0"
              :to="item.to"
              rounded
              color="primary"
              :disabled="item.disabled"
              :target="item.type === 'external' ? '_blank' : ''"
          >
            <v-expansion-panel-title>
              <Icon class="mr-3" :item="item.icon" :level="level" />
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-expansion-panel-title>

            <!--Subitems-->
            <v-expansion-panel-text>
              <v-list class="ma-0 pa-0">
                <template v-for="(subItem, i) in item.subItems" :key="subItem.title">
                  <v-list-item
                      :to="subItem.to"
                      rounded
                      class="ma-0 pa-0"
                      color="primary"
                      :disabled="subItem.disabled"
                      :target="subItem.type === 'external' ? '_blank' : ''"
                  >
                    <template v-slot:prepend>
                      <Icon :item="subItem.icon" :level="level" />
                    </template>
                    <v-list-item-title>{{ subItem.title }}</v-list-item-title>
                    <!--- If Caption -->
                    <v-list-item-subtitle v-if="subItem.subCaption" class="text-caption mt-n1 hide-menu">
                      {{ subItem.subCaption }}
                    </v-list-item-subtitle>
                    <!--- If any chip or label -->
                  </v-list-item>
                </template>
              </v-list>
            </v-expansion-panel-text>

          </v-list-item>

      </v-expansion-panel>
    </v-expansion-panels>
  </template>

  <template v-else>
    <v-list-item
        :to="item.to"
        rounded
        class="mb-1"
        color="primary"
        :disabled="item.disabled"
        :target="item.type === 'external' ? '_blank' : ''"
    >
      <template v-slot:prepend>
        <Icon :item="item.icon" :level="level" />
      </template>
      <v-list-item-title>{{ item.title }}</v-list-item-title>
      <!--- If Caption -->
      <v-list-item-subtitle v-if="item.subCaption" class="text-caption mt-n1 hide-menu">
        {{ item.subCaption }}
      </v-list-item-subtitle>
      <!--- If any chip or label -->
      <template v-slot:append v-if="item.chip">
        <v-chip
            :color="item.chipColor"
            class="sidebarchip hide-menu"
            :size="'small'"
            :variant="item.chipVariant"
            :prepend-icon="item.chipIcon"
        >
          {{ item.chip }}
        </v-chip>
      </template>
    </v-list-item>
  </template>
</template>
