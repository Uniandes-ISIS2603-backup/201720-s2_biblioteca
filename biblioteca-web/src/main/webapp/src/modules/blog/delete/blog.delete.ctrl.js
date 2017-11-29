(function (ng) {
    var mod = ng.module("blogsModule");
    mod.controller('blogsDeleteCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            var idBlog= $state.params.blogId;
            $scope.deleteBlog = function () {
                 $http.delete('api/999/blog' + '/' + idBlog, {}).then(function (response) {
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);