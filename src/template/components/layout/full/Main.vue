<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import {BuildingWarehouseIcon, LayoutBoardIcon, Menu2Icon, DotsVerticalIcon, PlusIcon} from 'vue-tabler-icons';
import { getSidebarItems, type Menu } from '@/components/layout/full/vertical-sidebar/sidebarItem';
import Icon from "~/components/layout/full/vertical-sidebar/Icon.vue";
import type {Workspace, WorkspaceDTO} from "~/types/Workspace";

const sDrawer = ref(true);
const menuItems = shallowRef<Menu[]>([]);

const {fetchWorkspaces, deleteWorkspace,workspaces,saveWorkspace} = useWorkspace();

onMounted(async () => {
  try {
    menuItems.value = await getSidebarItems();
    await fetchWorkspaces();
  } catch (error) {
    console.error('Failed to load sidebar items', error);
  }
});


const isModalOpen = ref(false);

const handleModalOpen = (value: boolean) => {
  isModalOpen.value = value;
};

const handleModalClose = () => {
  isModalOpen.value = false;
};

const editItem = (workspace: Workspace) => {
  console.log('Edit workspace:', workspace);
  // Add your edit logic here
};

const deleteItem = (workspace: Workspace) => {
  console.log('Delete workspace:', workspace);
  deleteWorkspace(workspace.id)
};

const showAddWorkspace = ref(false);
const newWorkspaceName = ref('');
const newWorkspaceDescription = ref('');

const addWorkspaceEvent = () => {
  const userId = localStorage.getItem('userId');
  if (!userId) {
    throw new Error('User ID is not available');
  }

  if (newWorkspaceName.value.trim()) {

    const newWorkspace: WorkspaceDTO = {
      name: newWorkspaceName.value,
      description: newWorkspaceDescription.value,
      userId: userId,
    };

    saveWorkspace(newWorkspace);
    newWorkspaceName.value = '';
    newWorkspaceDescription.value = '';
    showAddWorkspace.value = false;
  }
};

</script>

<template>
    <!------Sidebar-------->
    <v-navigation-drawer left elevation="0"  app class="leftSidebar"  v-model="sDrawer">
        <!---Logo part -->
        <div class="pa-5">
            <LayoutFullLogo />
        </div>
        <!-- ---------------------------------------------- -->
        <!---Navigation -->
        <!-- ---------------------------------------------- -->
        <div>
        <perfect-scrollbar class="scrollnavbar">
            <v-list class="pa-6">
                <!---Menu Loop -->
                <template v-for="(item, i) in menuItems" :key="item.title || item.header">
                    <!---Item Sub Header -->
                    <LayoutFullVerticalSidebarNavGroup :item="item" v-if="item.header" :key="item.title" @update:isModalOpen="handleModalOpen" />
                    <!---Single Item-->
                    <LayoutFullVerticalSidebarNavItem :item="item" v-else-if="!item.header" class="leftPadding" />

                    <v-dialog  v-model="isModalOpen" max-width="600px">

                      <v-card>

                        <v-card-title class="d-flex justify-space-between">
                          {{ "Workspace Settings" }}
                          <v-btn icon class="ml-auto " @click="handleModalClose" small>
                            <XIcon size="20" stroke-width="1.5"></XIcon>
                          </v-btn>
                        </v-card-title>

                        <v-card-text>

                          <div class="mb-3">
                            <v-btn icon class="ml-auto" color="primary" small @click="showAddWorkspace = true">
                              <PlusIcon size="20" stroke-width="1.5"></PlusIcon>
                            </v-btn>
                            <span class="ml-3">Add Workspace</span>
                          </div>

                         <!-- Add Workspace Modal -->
                          <v-dialog v-model="showAddWorkspace" max-width="500px">
                            <v-card>
                              <v-card-title>Add New Workspace</v-card-title>
                              <v-card-text>
                                <v-text-field v-model="newWorkspaceName" label="Workspace Name"></v-text-field>
                                <v-text-field v-model="newWorkspaceDescription" label="Workspace Description"></v-text-field>
                              </v-card-text>
                              <v-card-actions>
                                <v-btn color="primary" @click="addWorkspaceEvent">Add</v-btn>
                                <v-btn @click="showAddWorkspace = false">Cancel</v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>

                          <template  v-for="(workspace, i) in workspaces" :key="workspace.id">
                            <v-expansion-panels>
                              <v-expansion-panel>
                                <v-list-item
                                    class="pa-0 ma-0"
                                    rounded
                                    color="primary"
                                >
                                  <v-expansion-panel-title>
                                    <Icon class="mr-3" :item="BuildingWarehouseIcon" />
                                    <v-list-item-title>{{ workspace.name }}</v-list-item-title>
                                    <v-spacer></v-spacer>
                                    <v-menu
                                        offset-y
                                        transition="slide-y-transition"
                                    >
                                      <template v-slot:activator="{ isActive, props, targetRef }">
                                        <v-btn icon @click.stop v-bind="props">
                                          <DotsVerticalIcon />
                                        </v-btn>
                                      </template>
                                      <v-list>
                                        <v-list-item @click="() => editItem(workspace)">
                                          <v-list-item-title>Edit</v-list-item-title>
                                        </v-list-item>
                                        <v-list-item @click="() => deleteItem(workspace)">
                                          <v-list-item-title>Delete</v-list-item-title>
                                        </v-list-item>
                                      </v-list>
                                    </v-menu>
                                  </v-expansion-panel-title>

                                  <!--Subitems-->
                                  <v-expansion-panel-text>
                                    <v-list class="ma-0 pa-0">
                                      <template v-for="(project, i) in workspace.projects" :key="project.id">
                                        <v-list-item
                                            rounded
                                            class="ma-0 pa-0"
                                            color="primary"
                                        >
                                          <template v-slot:prepend>
                                            <Icon :item="LayoutBoardIcon"  />
                                          </template>
                                          <v-list-item-title>{{ project.name }}</v-list-item-title>
                                          <!--- If Caption -->
                                          <v-list-item-subtitle v-if="project.description" class="text-caption mt-n1 hide-menu">
                                            {{ project.description }}
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
                        </v-card-text>

                        <v-card-actions>
                          <v-btn color="primary">Save</v-btn>
                          <v-btn @click="handleModalClose">Cancel</v-btn>
                        </v-card-actions>

                    </v-card>

                  </v-dialog>
                    <!---End Single Item-->
                </template>
            </v-list>
        </perfect-scrollbar>
    </div>

    </v-navigation-drawer>
    <!------Header-------->
    <v-app-bar elevation="0" height="70">
        <div class="d-flex align-center justify-space-between w-100">
            <div>
                <v-btn class="hidden-lg-and-up ms-md-3 ms-sm-5 ms-3 text-muted" @click="sDrawer = !sDrawer" icon variant="flat"
                    size="small">
                    <Menu2Icon size="20" stroke-width="1.5" />
                </v-btn>
                <!-- Notification -->
                <LayoutFullVerticalHeaderNotificationDD/>
            </div>
            <div>
              <!-- User Profile -->
                <LayoutFullVerticalHeaderProfileDD />
            </div>
        </div>
    </v-app-bar>
</template>
