(function (ng) {
    var mod = ng.module("videosModule");
    mod.constant("videosContext", "api/videos");
    mod.controller('videosCtrl', ['$scope', '$http', 'videosContext',
        function ($scope, $http, videosContext) {
            $http.get('data/videos.json').then(function (response) {
                $scope.videoRecords = response.data;
            });
        }
    ]);
}
)(angular);