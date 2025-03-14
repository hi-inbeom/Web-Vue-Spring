<template>
    <div class="leftbar-wrapper">
        <ul>
            <li onclick="document.querySelector('#leftbar-home').click()">
                <router-link class="leftbar-router" id="leftbar-home" to="/" >
                    Home
                </router-link>
            </li>
            <hr class="leftbar-liner"/>
            <!-- <li onclick="document.querySelector('#leftbar-infinity').click()">
                <router-link class="leftbar-router" id="leftbar-infinity" to="/infinity">
                    Infinity Board
                </router-link>
            </li> -->
            <li onclick="document.querySelector('#leftbar-list').click()">
                <router-link class="leftbar-router" id="leftbar-list" to="/list">
                    List Board
                </router-link>
            </li>
            <hr class="leftbar-liner">
            <li onclick="document.querySelector('#leftbar-help').click()">
                <a 
                    class="leftbar-router"
                    id="leftbar-help"
                    href="/doc/Help.html" 
                    target="_blank"
                >
                Help
                </a>
            </li>
            <li onclick="document.querySelector('#leftbar-useragreement').click()">
                <a 
                    class="leftbar-router" 
                    id="leftbar-useragreement"
                    href="/doc/UserAgreement.html" 
                    target="_blank"
                >
                    User Agreement
                </a>
            </li>
            <li onclick="document.querySelector('#leftbar-privacypolicy').click()">
                <a 
                    class="leftbar-router" 
                    id="leftbar-privacypolicy"
                    href="/doc/PrivacyPolicy.html" 
                    target="_blank"
                >
                    Privacy Policy
                </a>
            </li>
        </ul>
    </div>
</template>
<script setup>
</script>
<style scoped>
    .leftbar-wrapper {
        width: 15%;
        border-right: 1px solid rgb(169, 169, 169);
    }
    .leftbar-wrapper ul {
        position: sticky;
        top: 34px;
        list-style-type: none;
        font-size: 19px;
        font-weight: 800;
        padding-left: 10px;
        padding-right: 10px;
    }
    .leftbar-wrapper li {
        padding-left: 15px;
        margin: 16px 0px 16px 0px;
        align-items: left;
        text-align: left;
        overflow-y: hidden;
        overflow-x: hidden;
        text-overflow: ellipsis;
        color: black;
        cursor: pointer;
    }
    .leftbar-wrapper li:hover  {
        background-color: rgba(70, 124, 94, 0.377);
        color: rgba(255, 255, 255, 0.856);
        border-radius: 10px;
    }
    .leftbar-wrapper li:hover a  {
        color: rgba(255, 255, 255, 0.856);
    }
    .leftbar-router {
        text-decoration: none;
        color: black;
    }
    .leftbar-router:hover  {
        color: rgba(255, 255, 255, 0.856);
    }
    .leftbar-liner {
        margin: 8px 0px 8px 0px;
    }
</style>