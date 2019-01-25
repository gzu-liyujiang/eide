

//license wtfpl 2.0

//by aenu 2019
//   email:202983447@qq.com

package aenu.eide.gradle_impl;
import aenu.gradle.G_Tree;

public interface IPlugin
{
    public void plugin_Visit(G_Tree tree);
    public Runnable plugin_Task(final GradleProject gp);
}
