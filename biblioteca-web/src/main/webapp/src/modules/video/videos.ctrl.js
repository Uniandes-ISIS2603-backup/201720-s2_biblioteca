(function (ng) {
    var mod = ng.module("videoModule");
    mod.constant("videoContext", "api/videos");
    mod.controller('videoCtrl', ['$scope', '$http', 'videoContext',
        function ($scope, $http, videoContext) {
            $http.get('data/videos.json').then(function (response) {
                $scope.videoRecords = response.data;
            });
        }
    ]);
}
)(angular);