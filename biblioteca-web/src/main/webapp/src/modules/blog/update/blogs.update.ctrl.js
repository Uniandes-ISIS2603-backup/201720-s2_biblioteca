(
        function (ng) {
            var mod = ng.module("blogModule");
            mod.constant("blogContext", "api/999/blogs");
            mod.controller('blogUpdateCtrl', ['$scope', '$http', 'blogsContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, blogContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idBlog = $state.params.blogId;

                    //Consulto el blog a editar.
                    console.info(blogContext+'/' + idBlog);
                    $http.get(blogContext + '/' + idBlog).then(function (response) {
                        var blog = response.data;
                        $scope.blogName = blog.name;
                        $scope.blogCalificacion = blog.calificacion;
        
                    });        
                    

                    $scope.createBlog = function () {                   
                        $http.put(blogContext + "/" + idBiblioteca, {
                            name: $scope.blogName,
                            calificacion: $scope.blogCalificacion,
                        }).then(function (response) {                            
                            $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(window.angular);