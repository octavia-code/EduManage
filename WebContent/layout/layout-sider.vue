<template>
    <div id="layout-sider" class="layout">
        <layout>
            <sider hide-trigger>
                <i-menu theme="dark" :active-name="activeName" :open-names="openMenu" @on-select="onSelectMenu"
                        width="auto" ref="sider_menu">
                    <!--</div>-->
                    <submenu v-for="submenu in menuList" :name="submenu.name" :key="submenu.id">
                        <template slot="title">
                            {{submenu.title}}
                        </template>
                        <menu-item v-for="menu in submenu.menuList" :name="menu.name" :key="menu.id">
                            {{menu.title}}
                        </menu-item>
                    </submenu>
                </i-menu>
            </sider>
        </layout>

    </div>
</template>
<script type="text/javascript" src="../layout/js/layout-sider.js"></script>