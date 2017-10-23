(function (ng) {
    var mod = ng.module("blogModule");
    mod.constant("blogsContext", "api/blogs");
    mod.controller('blogsCtrl', ['$scope', '$http', 'blogsContext',
        function ($scope, $http, blogsContext) {
            $http.get('data/blogs.json').then(function (response) {
                $scope.blogsRecords = response.data;
            });
        }
    ]);
}
)(angular);