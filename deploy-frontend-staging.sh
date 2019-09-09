#!/bin/bash

set -e

docker build -t gcr.io/${PROJECT_NAME_STG}/${FRONTEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT $FRONTEND_BUILD_DIR

echo $GCLOUD_SERVICE_KEY_STG | base64 --decode -i - > ${HOME}/gcloud-service-key.json
gcloud auth activate-service-account --key-file ${HOME}/gcloud-service-key.json

gcloud --quiet config set project $PROJECT_NAME_STG
gcloud --quiet config set container/cluster $CLUSTER_NAME_STG
gcloud --quiet config set compute/zone ${CLOUDSDK_COMPUTE_ZONE}
gcloud --quiet container clusters get-credentials $CLUSTER_NAME_STG

cat ${HOME}/gcloud-service-key.json | docker login -u _json_key --password-stdin https://gcr.io
docker push gcr.io/${PROJECT_NAME_STG}/${FRONTEND_DOCKER_IMAGE_NAME}

yes | gcloud container images add-tag gcr.io/${PROJECT_NAME_STG}/${FRONTEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT gcr.io/${PROJECT_NAME_STG}/${FRONTEND_DOCKER_IMAGE_NAME}:latest

kubectl config view
kubectl config current-context

kubectl set image deployment/${FRONTEND_KUBE_DEPLOYMENT_NAME} ${FRONTEND_KUBE_DEPLOYMENT_CONTAINER_NAME}=gcr.io/${PROJECT_NAME_STG}/${FRONTEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT

# sleep 30
# npm run e2e_test