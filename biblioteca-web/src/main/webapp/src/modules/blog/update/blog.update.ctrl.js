(
        function (ng) {
            var mod = ng.module("blogsModule");
            mod.constant("blogsContext", "api/999/blogs");
            mod.controller('blogsUpdateCtrl', ['$scope', '$http', 'blogsContext', '$state', 'blogsContext', '$rootScope',
                function ($scope, $http, blogsContext, $state, blogsContext, $rootScope) {
                    $rootScope.edit = true;

                    var idBlog = $state.params.blogId;

                    //Consulto el blog a editar.
                       console.info(blogsContext+'/' + idBlog);
                    $http.get(blogsContext + '/' + idBlog).then(function (response) {
                        var blog = response.data;
                        $scope.blogName = blog.name;
                        $scope.blogAutor = blog.autor;
                    });           
                    

                    $scope.createBlog = function () {                   
                        $http.put(blogsContext + '/' + idBlog, {
                          name: $scope.blogName,
                   autor: $scope.blogAutor,
                  
                        }).then(function (response) {                            
                            $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);