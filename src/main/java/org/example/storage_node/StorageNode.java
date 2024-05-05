package org.example.storage_node;

import java.util.HashSet;
import java.util.Set;

public class StorageNode {

    private String host;
    private String name;
    private Set<String> files;

    public StorageNode(String name, String host) {
        this.name = name;
        this.host = host;
        this.files = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "StorageNode{" +
            "host='" + host + '\'' +
            ", name='" + name + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StorageNode that = (StorageNode) o;

        if (!host.equals(that.host)) {
            return false;
        }
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public void addFile(String file){
        this.files.add(file);
    }

    public void removeFile(String file){
        this.files.remove(file);
    }

    public void addFiles(Set<String> files){
        this.files.addAll(files);
    }

    public Set<String> getFiles(){
        return this.files;
    }
}
