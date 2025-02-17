<template>
    <div class="leftbar-wrapper">
        <ul>
            <li>
                <router-link class="leftbar-router" to="/" >
                    Home
                </router-link>
            </li>
            <li class="leftbar-liner" style="height: 0px;"></li>
            <li>
                <router-link class="leftbar-router" to="/infinity">
                    Infinity Board
                </router-link>
            </li>
            <li>
                List Board
            </li>
            <li class="leftbar-liner" style="height: 0px;"></li>
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
<script>
export default {
    data() {
    }
};
</script>
<style scoped>
    .leftbar-wrapper {
        width: 15%;
        border-right: 1px solid rgb(169, 169, 169);
        padding-top: 10px;
    }
    .leftbar-wrapper ul {
        position: sticky;
        top: 10px;
        list-style-type: none;
        font-size: 19px;
        font-weight: 800;
        padding-left: 10px;
        padding-right: 10px;
    }
    .leftbar-wrapper li {
        padding-left: 15px;
        margin-bottom: 20px;
        height:25px;
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
    .leftbar-liner {
        border-bottom: 1px solid black;
    }
    .leftbar-router {
        text-decoration: none;
        color: black;
    }
    .leftbar-router:hover  {
        color: rgba(255, 255, 255, 0.856);
    }
</style>