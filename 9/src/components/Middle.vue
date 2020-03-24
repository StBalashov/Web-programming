<template>
    <div class="middle">
        <Sidebar :users="users" :posts="posts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="posts" :users="users"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users :users="users" v-if="page === 'Users'"/>
            <AddPost v-if="page === 'AddPost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Post :post="currentPost" :users="users" v-if="page === 'Post'"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import AddPost from './middle/AddPost';
    import Sidebar from './middle/Sidebar';
    import EditPost from "./middle/EditPost";
    import Users from "./middle/Users";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ['users', 'posts'],
        data: function () {
            return {
                page: "Index",
                currentPost: undefined
            }
        },
        components: {
            EditPost,
            Index,
            Enter,
            Register,
            Sidebar,
            Users,
            AddPost,
            Post
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page) => {
                this.page = page;
            });
            this.$root.$on("onViewPost", (postId) => {
                this.page = "Post";
                this.currentPost = this.posts[postId];
            });
        }
    }
</script>

<style scoped>

</style>
