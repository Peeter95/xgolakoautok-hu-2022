
### How to add a Git submodule?

git submodule add * submodules/*

### How to move a Git submodule?

git mv submodules/submodule-from submodules/submodule-to

### How to remove Git commit?

Interactive rebase -> Drop

### Why the Git manager ask you for a username and password?

That's because you cloned the repository with HTTPS authentication:
`https://github.com/username/repository.git`

So instead of using this method you have to clone with SSH authentication:
`git@github.com:username/repository.git`

If you added a submodule with https authentication, it might be the easiest
way to change the authentication method is to remove the submodule and add
it again to you project in your Git manager application.
