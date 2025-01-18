<template>
    <div class="leftbar-wrapper">
        <ul>
            <li>
                <router-link class="leftbar-router" to="/" >
                    Home
                </router-link>
            </li>
            <li>
                Popular
            </li>
            <li class="leftbar-liner" style="height: 0px;">
            </li>
            <li>
                + Create Community
            </li>
            <li v-for="(community, index) in userJoinedCommunities" :key="index">
                {{ community }}
            </li>
            <li class="leftbar-liner" style="height: 0px;">
            </li>
            <li>
                <a 
                    class="leftbar-router" 
                    :href="$router.resolve({ path: '/doc/help' }).href" 
                    target="_blank"
                >
                Help
                </a>
            </li>
            <li>
                <a 
                    class="leftbar-router" 
                    :href="$router.resolve({ path: '/doc/user-agreement' }).href" 
                    target="_blank"
                >
                User Agreement
                </a>
            </li>
            <li>
                <a 
                    class="leftbar-router" 
                    :href="$router.resolve({ path: '/doc/privacy-policy' }).href" 
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
    name: "LeftBar",
    data() {
        return {
            userJoinedCommunities: [
                "Vue.js Developers",
                "Node.js Enthusiasts",
                "Frontend Masters",
                "Backend Wizards"
            ]
        };
    }
};
</script>
<style>
    .leftbar-wrapper ul {
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