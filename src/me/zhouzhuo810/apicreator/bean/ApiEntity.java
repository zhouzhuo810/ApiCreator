package me.zhouzhuo810.apicreator.bean;

import java.util.List;

/**
 * Created by admin on 2017/5/19.
 */
public class ApiEntity {

    private ProjectBean project;
    private List<ModulesBean> modules;

    public ProjectBean getProject() {
        return project;
    }

    public void setProject(ProjectBean project) {
        this.project = project;
    }

    public List<ModulesBean> getModules() {
        return modules;
    }

    public void setModules(List<ModulesBean> modules) {
        this.modules = modules;
    }

    public static class ProjectBean {

        private String createTime;
        private String description;
        private String details;
        private String editable;
        private String id;
        private String name;
        private String permission;
        private String status;
        private String userId;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getEditable() {
            return editable;
        }

        public void setEditable(String editable) {
            this.editable = editable;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class ModulesBean {

        private String createTime;
        private String id;
        private String lastUpdateTime;
        private String name;
        private String projectId;
        private String requestArgs;
        private String requestHeaders;
        private List<FoldersBean> folders;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getRequestArgs() {
            return requestArgs;
        }

        public void setRequestArgs(String requestArgs) {
            this.requestArgs = requestArgs;
        }

        public String getRequestHeaders() {
            return requestHeaders;
        }

        public void setRequestHeaders(String requestHeaders) {
            this.requestHeaders = requestHeaders;
        }

        public List<FoldersBean> getFolders() {
            return folders;
        }

        public void setFolders(List<FoldersBean> folders) {
            this.folders = folders;
        }

        public static class FoldersBean {

            private String createTime;
            private String id;
            private String moduleId;
            private String name;
            private String projectId;
            private int sort;
            private String ip;
            private List<ChildrenBean> children;

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getModuleId() {
                return moduleId;
            }

            public void setModuleId(String moduleId) {
                this.moduleId = moduleId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {

                private String contentType;
                private String createTime;
                private String dataType;
                private String description;
                private String example;
                private String folderId;
                private String id;
                private String lastUpdateTime;
                private String moduleId;
                private String name;
                private String projectId;
                private String protocol;
                private String requestArgs;
                private String requestHeaders;
                private String requestMethod;
                private String responseArgs;
                private int sort;
                private String status;
                private String url;

                public String getContentType() {
                    return contentType;
                }

                public void setContentType(String contentType) {
                    this.contentType = contentType;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getDataType() {
                    return dataType;
                }

                public void setDataType(String dataType) {
                    this.dataType = dataType;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getExample() {
                    return example;
                }

                public void setExample(String example) {
                    this.example = example;
                }

                public String getFolderId() {
                    return folderId;
                }

                public void setFolderId(String folderId) {
                    this.folderId = folderId;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLastUpdateTime() {
                    return lastUpdateTime;
                }

                public void setLastUpdateTime(String lastUpdateTime) {
                    this.lastUpdateTime = lastUpdateTime;
                }

                public String getModuleId() {
                    return moduleId;
                }

                public void setModuleId(String moduleId) {
                    this.moduleId = moduleId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getProjectId() {
                    return projectId;
                }

                public void setProjectId(String projectId) {
                    this.projectId = projectId;
                }

                public String getProtocol() {
                    return protocol;
                }

                public void setProtocol(String protocol) {
                    this.protocol = protocol;
                }

                public String getRequestArgs() {
                    return requestArgs;
                }

                public void setRequestArgs(String requestArgs) {
                    this.requestArgs = requestArgs;
                }

                public String getRequestHeaders() {
                    return requestHeaders;
                }

                public void setRequestHeaders(String requestHeaders) {
                    this.requestHeaders = requestHeaders;
                }

                public String getRequestMethod() {
                    return requestMethod;
                }

                public void setRequestMethod(String requestMethod) {
                    this.requestMethod = requestMethod;
                }

                public String getResponseArgs() {
                    return responseArgs;
                }

                public void setResponseArgs(String responseArgs) {
                    this.responseArgs = responseArgs;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
